package com.xyz.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * This Activity demonstrates Google sign in, FCM notification receiving, How to fetch a string from remote config,
 * how to fetch a JSON object and parse it, how to add an image into firebase storage and fetch it.
 */
class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val SIGNIN_REQ_CODE = 101
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initListeners()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("debug", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            println("debug: new token ${token}")
        })
    }

    private fun initListeners() {
        btnSignIn.setOnClickListener(this)
        btnSignOut.setOnClickListener(this)
        btnFetch.setOnClickListener(this)
        btnFetchPrateek.setOnClickListener(this)
        tvHello.text = FirebaseConfig.remoteConfig.getString("hello_msg")
    }


    private fun initData() {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        auth = Firebase.auth

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGNIN_REQ_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Log.d("TAG", "firebaseAuthWithGoogle:" + account?.id)
                firebaseAuthWithGoogle(account?.idToken)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Snackbar.make(rlParent, "SigIn successfull", Snackbar.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    // ...
                    println("Failure")
                    Snackbar.make(rlParent, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                }

            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignIn -> {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, SIGNIN_REQ_CODE)
            }

            R.id.btnSignOut -> {
                auth.signOut()
            }

            R.id.btnFetch -> {
                fetchTrojansData()
            }

            R.id.btnFetchPrateek -> {
                fetchImageFromStorage()
            }
        }
    }

    private fun fetchImageFromStorage() {
        Glide.with(ivPrateek).load(FirebaseConfig.remoteConfig.getString("image_prateek"))
            .into(ivPrateek)
    }

    private fun fetchTrojansData() {
        val trojansStringJson = FirebaseConfig.remoteConfig.getString("test_json")

        val trojansArray = JSONArray(trojansStringJson)
        for (i in 0 until trojansArray.length()) {
            val json: JSONObject = trojansArray.getJSONObject(i)
            val id = json.getString("id")
            val name = json.getString("name")
            val text = "${tvTrojansData.text} \n $id \n $name"
            tvTrojansData.text = text
        }
    }
}