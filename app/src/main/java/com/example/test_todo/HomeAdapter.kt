package com.example.test_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.model.User

class HomeAdapter(var userList: List<User>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

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
    };


};