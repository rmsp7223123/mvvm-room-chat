package com.example.test_todo.repository

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.lifecycle.LiveData
import com.example.test_todo.data.ChatDao
import com.example.test_todo.model.Chat
import com.example.test_todo.model.User
import com.google.firebase.database.FirebaseDatabase

class ChatRepository(private val chatDao : ChatDao) {

    val readAllData: LiveData<List<Chat>> = chatDao.readAllData();

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("chats");

    suspend fun addChat(chat: Chat){
        chatDao.addChat(chat);
    };

    fun sendMessage(senderId: String, receiverId: String, message: String, senderNickname : String, receiverNickname: String) {
        val chatId = databaseReference.push().key ?: return;
        val timestamp = System.currentTimeMillis();

        val chatData = mapOf(
            "senderId" to senderId,
            "receiverId" to receiverId,
            "senderNickname" to senderNickname,
            "receiverNickname" to receiverNickname,
            "message" to message,
            "timestamp" to timestamp
        );
        databaseReference.child(chatId).setValue(chatData);
    };
}