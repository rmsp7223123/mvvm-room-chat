package com.example.test_todo.repository

import androidx.lifecycle.LiveData
import com.example.test_todo.data.ChatDao
import com.example.test_todo.model.Chat
import com.example.test_todo.model.User

class ChatRepository(private val chatDao : ChatDao) {

    val readAllData: LiveData<List<Chat>> = chatDao.readAllData();

    suspend fun addChat(chat: Chat){
        chatDao.addChat(chat);
    };
}