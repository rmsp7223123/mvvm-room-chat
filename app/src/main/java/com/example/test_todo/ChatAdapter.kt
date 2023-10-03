package com.example.test_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.databinding.ItemRecvMessageBinding

class ChatAdapter() : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecvMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    };

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    };

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    };

    inner class ViewHolder(var binding : ItemRecvMessageBinding) : RecyclerView.ViewHolder(
        binding.root
    );
};