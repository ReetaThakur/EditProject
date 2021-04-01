package com.masai.androidsqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context: Context) :
    SQLiteOpenHelper(context, DBNAME, null, DBVersion) {

    companion object{
        private val DBNAME = "olxdb"
        private val DBVersion = 1

        private val TABLE_NAME = "ads_table"
        private val ID = "id"
        private val TITLE = "title"
        private val DESC = "desc"
        private val PRICE = "price"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $TITLE TEXT, $DESC TEXT, $PRICE Integer)"

        db?.execSQL(CREATE_TABLE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun saveAd(adModel: AdModel): Boolean{
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put(TITLE, adModel.title)
        contentValues.put(DESC, adModel.desc)
        contentValues.put(PRICE, adModel.price)

        val id = db.insert(TABLE_NAME, null, contentValues)

        return (id.toInt() != -1)
    }

    fun getAds(): String{
        var result = ""
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        if (cursor != null){
            if (cursor.moveToFirst()){
                val title = cursor.getString(cursor.getColumnIndex(TITLE))
                val desc = cursor.getString(cursor.getColumnIndex(DESC))
                val price = cursor.getInt(cursor.getColumnIndex(PRICE))
                result = "$result\n $title  $desc $price"

                while (cursor.moveToNext()){
                    val title = cursor.getString(cursor.getColumnIndex(TITLE))
                    val desc = cursor.getString(cursor.getColumnIndex(DESC))
                    val price = cursor.getInt(cursor.getColumnIndex(PRICE))
                    result = "$result\n $title  $desc $price"
                }
            }
        }
        return result
    }

}