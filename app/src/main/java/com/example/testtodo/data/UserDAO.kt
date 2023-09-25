package com.example.testtodo.data

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.testtodo.model.User

interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : User);

    @Query("select * from user order by id asc")
    fun readAllData() : LiveData<List<User>>;

    @Update
    suspend fun updateUser(user :User);

    @Delete
    suspend fun deleteUser(user : User);

}