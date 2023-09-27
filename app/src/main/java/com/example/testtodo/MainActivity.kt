package com.example.testtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.databinding.ActivityMainBinding
import com.example.kotlin_todo2.fragments.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.tvUser.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction();
            val fragment = ListFragment();
            transaction.replace(R.id.fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        };
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    };
}