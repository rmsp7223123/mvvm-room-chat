package com.example.test_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test_todo.databinding.ActivityJoinBinding
import com.example.test_todo.model.User
import com.example.test_todo.repository.UserRepository
import com.example.test_todo.viewmodel.UserViewModel


class JoinActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJoinBinding;

    private lateinit var userViewModel: UserViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(layoutInflater);
        setContentView(binding.root);
        userViewModel =ViewModelProvider(this)[UserViewModel::class.java];
        binding.btnCreate.setOnClickListener {
            userViewModel.readAllData.observe(this) { users ->
                if (users == null || users.none { it.user_id == binding.edtId.text.toString() }) {
                    insertDataToDatabase(
                        binding.edtId.text.toString(),
                        binding.edtPw.text.toString(),
                        binding.edtNickname.text.toString()
                    );
                    Toast.makeText(this, "가입완료", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this, MainActivity::class.java));
                } else {
                    binding.edtId.text = null;
                    Toast.makeText(this, "중복된 id입니다.", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private fun insertDataToDatabase(user_id: String, user_pw: String, user_nickname: String) {

        if(inputCheck(user_id,user_pw,user_nickname)){
            val user = User(user_id, user_pw, user_nickname);
            userViewModel.addUser(user);
            Toast.makeText(this,"추가완료", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "추가실패", Toast.LENGTH_LONG).show();
        }
    }
    
    private fun inputCheck(user_id:String, user_pw:String, user_nickname: String):Boolean{
        return !(TextUtils.isEmpty(user_id)&& TextUtils.isEmpty(user_pw)&& TextUtils.isEmpty(user_nickname));
    }
}