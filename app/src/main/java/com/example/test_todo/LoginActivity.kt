package com.example.test_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root);
    };
}