package com.xyz.firebasechat.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R
import com.xyz.firebasechat.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
 * This activity is used to register a user and save the user details into the Firebase realtime database
 */
class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initData()
        initListeners()
    }

    private fun initListeners() {
        /*
        Create a user with username and password
         */
        btnRegister.setOnClickListener {
            flProgressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(
                etEmail.text.toString().trim(),
                etPassword.text.toString().trim()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        flProgressBar.visibility = View.GONE
                        updateFirebaseDatabase()
                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        flProgressBar.visibility = View.GONE
                    }
                }

        }
    }

    /**
     * This method is used to save the user details into the firebase realtime database
     */
    private fun updateFirebaseDatabase() {
        val userId = auth.currentUser?.uid!!
        databaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val user = User(auth.currentUser?.email!!, userId)
        //Adds the user to the database
        databaseReference.setValue(user).addOnCompleteListener {
            updateUI()
        }
    }

    private fun initData() {
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI()
        }
    }

    /**
     * Once the user is signed in and added to database then navigate him to the all participants
     * screen
     */
    private fun updateUI() {
        val intent = Intent(this@SignUpActivity, ParticipantsActivity::class.java)
        startActivity(intent)
        finish()
    }

}