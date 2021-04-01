package com.masai.androidsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.masai.androidsqlite.database.AdModel
import com.masai.androidsqlite.database.DbHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHandler = DbHandler(this)

        val result = dbHandler.getAds()
        adsListTv.text = result


        registerBtn.setOnClickListener {
            val title = titleEt.text.toString()
            val desc = descEt.text.toString()
            val price = priceEt.text.toString()

            val adModel = AdModel()
            adModel.title = title
            adModel.desc = desc
            adModel.price = price.toInt()

            val isSuccess = dbHandler.saveAd(adModel)

            if (isSuccess) {
                Toast.makeText(this, "Ad published successfully",
                    Toast.LENGTH_SHORT).show()
                titleEt.setText("")
                descEt.setText("")
                priceEt.setText("")

            }else{
                Toast.makeText(this, "Something went wrong!!",
                    Toast.LENGTH_SHORT).show()
            }

        }

    }
}