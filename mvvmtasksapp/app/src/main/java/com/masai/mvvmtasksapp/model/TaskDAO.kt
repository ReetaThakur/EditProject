package com.masai.mvvmtasksapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewTask(taskEntity: TaskEntity)

    @Query("select id, tile, `desc` from tasks ")
    fun getTasks(): LiveData<List<TaskEntity>>

}