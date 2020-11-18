package com.xyz.firebasedemo

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * This is firebase class which will set the default properties.
 */
class FirebaseConfig {

    companion object {
        val remoteConfig = Firebase.remoteConfig

        fun init() {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }

            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(
                R.xml.default_config
            )

            /*
             * This method gets called as soon as the firebase is ready to fetch from DB
             */
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val updated = it.result
                        Log.d("Lloyd", "Config params updated: $updated")
                    } else {
                        Log.d("Lloyd", "Config params updated: failed")

                    }
                }
        }


    }
}