package com.example.test_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.btnJoin.setOnClickListener { startActivity(Intent(this, JoinActivity::class.java)); };
        binding.btnLogin.setOnClickListener { startActivity(Intent(this,LoginActivity::class.java)); };
    };
}