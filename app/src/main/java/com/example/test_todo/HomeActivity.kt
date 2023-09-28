package com.example.test_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_todo.databinding.ActivityHomeBinding
import com.example.test_todo.model.User
import com.example.test_todo.viewmodel.UserViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding;
    private lateinit var userViewModel: UserViewModel;
    private lateinit var adapter: HomeAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        userViewModel.readAllData.observe(this, Observer { userList ->
           // adapter.submitList(userList);
        })

       // var adapter = HomeAdapter();
        binding.recv.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity);
            this.adapter = adapter;
        };
    };
}