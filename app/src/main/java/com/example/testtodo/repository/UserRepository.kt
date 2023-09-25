package com.example.testtodo.repository

import androidx.lifecycle.LiveData
import com.example.testtodo.data.UserDAO
import com.example.testtodo.model.User

class UserRepository(private val userDAO: UserDAO) {

    val readAllData: LiveData<List<User>> = userDAO.readAllData();

    suspend fun addUser(user: User){ // suspend를 붙여준 이유는 coroutine을 사용하기 위함, 코루틴을 사용하여 메인 스레드를 차단하지 않고 비동기 작업을 수행
        userDAO.addUser(user); //DAO에서 만들었던 adduser을 실행
    };

    suspend fun updateUser(user : User) {
        userDAO.updateUser(user);
    };

    suspend fun deleteUser(user : User) {
        userDAO.deleteUser(user);
    };
}