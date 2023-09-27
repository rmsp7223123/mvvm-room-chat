package com.example.kotlin_todo2.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin_todo2.R
import com.example.kotlin_todo2.databinding.FragmentUpdateBinding
import com.example.kotlin_todo2.model.User
import com.example.kotlin_todo2.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>(); // navArgs() 프래그먼트 간에 전달된 인수(argument)를 가져올 때 사용,
    // UpdateFragmentArgs 프래그먼트간 데이터 전달
    private lateinit var binding : FragmentUpdateBinding;
    private lateinit var mUserViewModel: UserViewModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUserViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java];
        binding = FragmentUpdateBinding.inflate(layoutInflater);
        binding.updateFirstNameEt.setText(args.currentUser.firstName);
        binding.updateLastNameEt.setText(args.currentUser.lastName);
        binding.updateAgeEt.setText(args.currentUser.age.toString());
        binding.updateButton.setOnClickListener {
            val firstName = binding.updateFirstNameEt.text.toString();
            val lastName = binding.updateLastNameEt.text.toString();
            val age = binding.updateAgeEt.text.toString();
            updateItem(firstName, lastName, age);
        };

        setHasOptionsMenu(true);

        return binding.root;
    }

    private fun updateItem(firstName: String, lastName: String, age: String){
        if (inputCheck(firstName,lastName,age)){
            val updatedUser = User(args.currentUser.id, firstName, lastName,age.toInt());
            mUserViewModel.updateUser(updatedUser);
            Toast.makeText(requireContext(),"변경완료",Toast.LENGTH_SHORT).show();
            findNavController().navigate(R.id.action_updateFragment_to_listFragment);
        } else{
            Toast.makeText(requireContext(),"변경완료 실패",Toast.LENGTH_SHORT).show();
        };
    };
    private fun inputCheck(firstName:String, lastName:String, age: String):Boolean{
        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&& age.isEmpty());
    };

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { // action menu resource파일을 연결
        inflater.inflate(R.menu.delete_menu, menu);
    };

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        };
        return super.onOptionsItemSelected(item);
    };

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext());
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteUser(args.currentUser);
            Toast.makeText(requireContext(),"삭제: ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show();
            findNavController().navigate(R.id.action_updateFragment_to_listFragment);
        };
        builder.setNegativeButton("No") { _, _ ->
        };

        builder.setTitle("Delete ${args.currentUser.firstName}${args.currentUser.lastName}?");
        builder.setMessage("Are you sure to delete ${args.currentUser.firstName}${args.currentUser.lastName}");

        builder.create().show();
    };
}