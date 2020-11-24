package com.xyz.firebasechat.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initData()
        initListeners()
    }

    private fun initListeners() {
        btnRegister.setOnClickListener {
            flProgressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(
                etEmail.text.toString().trim(),
                etPassword.text.toString().trim()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateUI()
                        flProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        flProgressBar.visibility = View.GONE
                    }
                }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
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

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, ParticipantsActivity::class.java)
        startActivity(intent)
        finish()
    }

}