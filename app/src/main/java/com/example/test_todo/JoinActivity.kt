package com.example.test_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_todo.databinding.ActivityJoinBinding


class JoinActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJoinBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.btnCreate.setOnClickListener {

        };
    }
}