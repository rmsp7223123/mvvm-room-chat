package com.example.test_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_todo.databinding.ActivityChatBinding
import com.example.test_todo.model.User
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding;

    private val firebaseDatabase = FirebaseDatabase.getInstance();

    private val databaseReference = firebaseDatabase.reference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val selectedUsers = intent.getSerializableExtra("selected_users") as? List<User>;

        binding.imgvBack.setOnClickListener { finish(); };
        if (!selectedUsers.isNullOrEmpty()) {
            binding.tvNickname.text = selectedUsers[0].user_nickname;
        };
    };
}