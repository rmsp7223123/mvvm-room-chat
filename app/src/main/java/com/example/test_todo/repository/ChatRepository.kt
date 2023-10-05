package com.example.test_todo.repository

import androidx.lifecycle.LiveData
import com.example.test_todo.data.ChatDao
import com.example.test_todo.model.Chat
import com.example.test_todo.model.User
import com.google.firebase.database.FirebaseDatabase

class ChatRepository(private val chatDao : ChatDao) {

    val readAllData: LiveData<List<Chat>> = chatDao.readAllData();

    private val databaseReference = FirebaseDatabase.getInstance().reference;

    suspend fun addChat(chat: Chat){
        chatDao.addChat(chat);
    };

    fun sendMessageToSelf(senderId: String, receiverId: String, message: String) {
        val chatId = databaseReference.child("chats").push().key!!;
        val timestamp = System.currentTimeMillis();

        val chatData = mapOf(
            "senderId" to senderId,
            "receiverId" to receiverId,
            "message" to message,
            "timestamp" to timestamp
        );

        databaseReference.child("chats").child(chatId).setValue(chatData);
    };

    fun sendMessageToReceiver(senderId: String, receiverId: String, message: String) {
        val chatId = databaseReference.child("chats").push().key!!;
        val timestamp = System.currentTimeMillis();

        val chatData = mapOf(
            "senderId" to senderId,
            "receiverId" to receiverId,
            "message" to message,
            "timestamp" to timestamp
        );

        databaseReference.child("chats").child(chatId).setValue(chatData);
    };
}