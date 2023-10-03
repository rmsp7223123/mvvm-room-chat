package com.example.test_todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val senderId: String,
    val receiverId: String,
    val senderNickname: String,
    val receiverNickname: String,
    val message: String,
    val time: Long
);