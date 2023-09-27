package com.example.test_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.test_todo.databinding.ActivityLoginBinding
import com.example.test_todo.viewmodel.UserViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding;

    private lateinit var userViewModel: UserViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root);
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java];
        binding.btnJoin.setOnClickListener {
            userViewModel.readAllData.observe(this) { users ->
                if (users.any { it.user_id == binding.edtId.text.toString() && it.user_pw == binding.edtPw.text.toString() }) {
                    startActivity(Intent(this, HomeActivity::class.java));
                } else {
                    Toast.makeText(this, "id또는 pw가 틀렸습니다.", Toast.LENGTH_LONG).show();
                }
            }
        };
    };
}