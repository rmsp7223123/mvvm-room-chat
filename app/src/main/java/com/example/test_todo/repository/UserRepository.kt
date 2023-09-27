package com.example.test_todo.repository

import androidx.lifecycle.LiveData
import com.example.test_todo.data.UserDao
import com.example.test_todo.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData();

    suspend fun addUser(user: User){
        userDao.addUser(user);
    };
}