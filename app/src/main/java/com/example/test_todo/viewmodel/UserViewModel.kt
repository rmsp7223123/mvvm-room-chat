package com.example.test_todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.test_todo.data.UserDatabase
import com.example.test_todo.model.User
import com.example.test_todo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData : LiveData<List<User>>;
    private val repository : UserRepository;
    private val _userList = MutableLiveData<List<User>>();
    // val userList: LiveData<List<User>> get() = _userList;

    init {
        val userDao = UserDatabase.getDatabase(application).userDao();
        repository = UserRepository(userDao);
        readAllData = repository.readAllData;
    };

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user);
        };
    };

    fun getCurrentUser(currentUserId: String): LiveData<List<User>> {
        return readAllData.map { userList ->
            userList.filter { it.user_id != currentUserId };
        };
    }
}