package com.example.testtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}