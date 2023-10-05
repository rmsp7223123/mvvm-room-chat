package com.example.test_todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.test_todo.common.CommonVar
import com.example.test_todo.databinding.ActivityChatBinding
import com.example.test_todo.model.User
import com.example.test_todo.viewmodel.ChatViewModel
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding;

    private val firebaseDatabase = FirebaseDatabase.getInstance();

    private val databaseReference = firebaseDatabase.reference;

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val selectedUsers = intent.getSerializableExtra("selected_users") as? List<User>;

        val opponentUserId = selectedUsers!![0].user_id;

        binding.imgvBack.setOnClickListener { finish(); };
        if (!selectedUsers.isNullOrEmpty()) {
            binding.tvNickname.text = selectedUsers[0].user_nickname;
        };

        binding.imgvSend.setOnClickListener {
          val message = binding.edtMessage.text.toString();
            if (message.isNotEmpty()) {
                val messageId = databaseReference.child("chat").child(CommonVar.user_id!!).push().key!!;
                binding.edtMessage.setText("");
            };
        };
    };
}