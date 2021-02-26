package com.masai.mvvmtasksapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDAO

    companion object{

        private var INSTANCE : TaskDatabase? = null

        fun getRoomDatabase(context: Context): TaskDatabase{

            if (INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database")

                builder.fallbackToDestructiveMigration()
                INSTANCE =  builder.build()
                return INSTANCE!!
            }else{
                return INSTANCE!!
            }

        }

    }

}