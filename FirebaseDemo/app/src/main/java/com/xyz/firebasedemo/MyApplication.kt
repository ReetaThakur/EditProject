package com.xyz.firebasedemo

import android.app.Application
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class MyApplication : Application() {

    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        super.onCreate()
        //Initialize the firebaseConfig class
        FirebaseConfig.init()
    }
}