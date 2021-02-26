package com.masai.mvvmtasksapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @ColumnInfo(name = "tile") val title: String,
    @ColumnInfo(name = "desc") val desc: String
) {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int? = null

}