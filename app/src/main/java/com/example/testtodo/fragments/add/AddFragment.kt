package com.example.kotlin_todo2.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBindings
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.model.User
import com.example.kotlin_todo2.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel; // UserViewModel 인스턴스를 저장하기 위한 mUserViewModel 필드를 선언

    override fun onCreateView(// 프래그먼트의 레이아웃을 초기화하고 화면에 표시할 View를 반환하는 역할
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false);
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]; // UserViewModel의 인스턴스를 생성하고 mUserViewModel 필드에 저장


        view.findViewById<Button>(R.id.add_button).setOnClickListener{
            val firstName = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString();
            val lastName = view.findViewById<EditText>(R.id.editTextTextPersonName2).text.toString();
            val age = view.findViewById<EditText>(R.id.editTextNumber).text.toString();
            insertDataToDatabase(firstName, lastName, age);
        }
        return view;
    }

    private fun insertDataToDatabase(firstName: String, lastName: String, age: String) { //사용자 데이터를 데이터베이스에 추가하는 역할

        if(inputCheck(firstName,lastName,age)){ // 모든 필드가 비어 있지 않을 경우 코드 블록이 실행
            val user = User(0, firstName, lastName, age.toInt());
            // autoGenerate = true일때 id 필드를 0으로 설정하면, Room 라이브러리가 자동으로 새로운 고유한 id 값을 생성하고 할당
            mUserViewModel.addUser(user);
            Toast.makeText(requireContext(),"추가완료", Toast.LENGTH_LONG).show();
            findNavController().navigate(R.id.action_addFragment2_to_listFragment);
        }else{
            Toast.makeText(requireContext(), "추가실패", Toast.LENGTH_LONG).show();
        }
    }

    //텍스트박스가 비어있는지 확인
    private fun inputCheck(firstName:String, lastName:String, age: String):Boolean{
        return !(TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&& age.isEmpty());
        // TextUtils.isEmpty() 문자열이 비어 있거나 null인지를 확인하기 위해 사용
    }




}