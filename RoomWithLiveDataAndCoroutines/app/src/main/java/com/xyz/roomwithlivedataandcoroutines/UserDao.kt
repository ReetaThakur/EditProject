package com.xyz.roomwithlivedataandcoroutines

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("Select * From users")
    suspend fun getAllUsers(): List<User>

    @Query("Update users SET firstName =:firstName, lastName =:lastName where userId =:id")
    suspend fun updateUserDetails(firstName: String, lastName: String, id: Int)

    @Delete
    suspend fun deleteUser(user: User)
}