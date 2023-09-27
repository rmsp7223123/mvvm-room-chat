package com.example.test_todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.test_todo.model.User

@Dao
interface UserDao {
    suspend fun addUser(user: User);

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>;
}