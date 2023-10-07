package com.example.test_todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_todo.common.CommonVar
import com.example.test_todo.data.ChatDatabase
import com.example.test_todo.databinding.ActivityChatBinding
import com.example.test_todo.model.Chat
import com.example.test_todo.model.User
import com.example.test_todo.repository.ChatRepository
import com.example.test_todo.viewmodel.ChatViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding;

    private val databaseReference = FirebaseDatabase.getInstance().reference;
    val chatReference = databaseReference.child("chats");

    private lateinit var chatViewModel: ChatViewModel;

    private lateinit var adapter: ChatAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val selectedUsers = intent.getSerializableExtra("selected_users") as? List<User>;
        val opponentUserId = selectedUsers!![0].user_id;
        val opponentUserNickname = selectedUsers!![0].user_nickname;
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java];
        chatViewModel.getCurrentChat(CommonVar.user_id!!).observe(this, Observer {chatList ->
           binding.recvMessageChat.adapter = ChatAdapter(chatList, this);
           binding.recvMessageChat.layoutManager = LinearLayoutManager(this);
        });

        binding.imgvBack.setOnClickListener { finish(); };
        if (!selectedUsers.isNullOrEmpty()) {
            binding.tvNickname.text = selectedUsers[0].user_nickname;
        };

        binding.imgvSend.setOnClickListener {
            val message = binding.edtMessage.text.toString();
            if (message.isNotEmpty()) {
                val chat = Chat(
                    senderId = CommonVar.user_id!!,
                    senderNickname = CommonVar.user_nickname!!,
                    receiverId = opponentUserId,
                    receiverNickname = opponentUserNickname,
                    message = message,
                    time = System.currentTimeMillis()
                );
                chatViewModel.addChat(chat);
                chatViewModel.sendMessage(CommonVar.user_id!!, opponentUserId, message);
                binding.edtMessage.setText("");
            };
        };

        chatReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatList = mutableListOf<Chat>();
                for (dataSnapshot in snapshot.children) {
                    val chat = dataSnapshot.getValue(Chat::class.java);
                    chat?.let {
                        chatList.add(it);
                    };
                };
                val adapter = ChatAdapter(chatList, applicationContext);
                binding.recvMessageChat.adapter = adapter;
            };

            override fun onCancelled(error: DatabaseError) {
            }
        })
    };
}