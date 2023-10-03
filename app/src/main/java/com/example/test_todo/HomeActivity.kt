package com.example.test_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_todo.common.CommonVar
import com.example.test_todo.databinding.ActivityHomeBinding
import com.example.test_todo.model.User
import com.example.test_todo.viewmodel.ChatViewModel
import com.example.test_todo.viewmodel.UserViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding;
    private lateinit var userViewModel: UserViewModel;
    private lateinit var adapter: HomeAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java];
        binding.tvId.text = CommonVar.user_id;
        binding.tvName.text = CommonVar.user_nickname;

        val currentUserId = CommonVar.user_id;

        userViewModel.getCurrentUser(currentUserId!!).observe(this, Observer { userList ->
            val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java];
            adapter = HomeAdapter(userList, this, chatViewModel);
            binding.recv.adapter = adapter;
            binding.recv.layoutManager = LinearLayoutManager(this);
        });
    };

    override fun onDestroy() {
        super.onDestroy();
        userViewModel.readAllData.removeObservers(this);
    };
}