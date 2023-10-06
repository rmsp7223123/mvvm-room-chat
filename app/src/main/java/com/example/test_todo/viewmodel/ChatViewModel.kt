package com.example.test_todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.test_todo.data.ChatDatabase
import com.example.test_todo.data.UserDatabase
import com.example.test_todo.model.Chat
import com.example.test_todo.model.User
import com.example.test_todo.repository.ChatRepository
import com.example.test_todo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Chat>>;
    private val repository : ChatRepository;

    init {
        val chatDao = ChatDatabase.getDatabase(application).chatDao();
        repository = ChatRepository(chatDao);
        readAllData = repository.readAllData;
    };

    fun addChat(chat: Chat) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChat(chat)
        };
    };

    fun sendMessage(senderId: String, receiverId: String, message: String) {
        repository.sendMessage(senderId, receiverId, message);
    };

    fun getCurrentChat(currentUserId: String): LiveData<List<Chat>> {
        return readAllData.map { chatList ->
            chatList.filter { it.senderId != currentUserId };
        };
    }
}