package com.example.test_todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_todo.model.Chat

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChat(chat : Chat);

    @Query("select * from chat")
    fun readAllData() : LiveData<List<Chat>>;
}