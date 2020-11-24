package com.xyz.firebasechat.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = Firebase.auth
    }

    override fun onResume() {
        super.onResume()
        Handler(mainLooper).postDelayed({

            /* Check if the user is already signed In, If yes directly show me all the participants screen
            or else navigate him to Login screen
            */
            if (auth.currentUser != null) {
                val intent = Intent(this@SplashActivity, ParticipantsActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)
    }
}