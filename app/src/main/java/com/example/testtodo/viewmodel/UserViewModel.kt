package com.example.testtodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testtodo.data.UserDatabase
import com.example.testtodo.model.User
import com.example.testtodo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData : LiveData<List<User>>;

    private val repository : UserRepository;


    init {
        val userDao = UserDatabase.getDatabase(application).userDAO();
        repository = UserRepository(userDao);
        readAllData = repository.readAllData;
    };

    fun addUser(user: User){ // 사용자를 추가하는 작업
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user);
        };
    };

    fun updateUser(user : User) {
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user);
        }
    };

    fun deleteUser(user : User) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user);
        }
    }
}