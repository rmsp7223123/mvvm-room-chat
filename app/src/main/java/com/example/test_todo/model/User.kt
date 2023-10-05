package com.example.test_todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val user_id : String,
    val user_pw : String,
    val user_nickname : String
) : Serializable