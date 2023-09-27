package com.example.test_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);
    };
}