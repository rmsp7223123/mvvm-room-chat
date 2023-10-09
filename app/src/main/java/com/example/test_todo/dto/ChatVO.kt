package com.example.test_todo.dto

import java.io.Serializable

data class ChatVO(var senderId : String, var senderNickname : String, var receiverId : String, var receiverNickname : String,
var message : String, var time : Long) : Serializable;