package com.masai.mvvmtasksapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewTask(taskEntity: TaskEntity)

    @Query("select id, tile, `desc` from tasks ")
    fun getTasks(): LiveData<List<TaskEntity>>

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

}