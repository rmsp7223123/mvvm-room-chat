package com.example.test_todo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.model.User
import com.example.test_todo.viewmodel.ChatViewModel
import java.io.Serializable

class HomeAdapter(var userList: List<User> ,private var context : Context, private var viewModel: ChatViewModel) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(var binding : ItemRecvHomeBinding) : RecyclerView.ViewHolder(
        binding.root
    );

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecvHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    };

    override fun getItemCount(): Int {
        return userList.size;
    };

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position];
        holder.binding.tvId.text = user.user_id;
        holder.binding.tvName.text = user.user_nickname;
        holder.binding.containerUser.setOnClickListener {
            val selectedUserList = listOf(user);
            viewModel.setSelectedUsers(selectedUserList);
            val intent = Intent(context, ChatActivity::class.java).apply {
                putExtra("selected_users", selectedUserList as Serializable);
            }
            context.startActivity(intent);
        };
    };


};