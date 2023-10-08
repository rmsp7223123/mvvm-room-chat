package com.example.test_todo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.databinding.ItemRecvMessageBinding
import com.example.test_todo.model.Chat

class ChatAdapter(var chatList : List<Chat>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecvMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    };

    override fun getItemCount(): Int {
        return chatList.size;
    };

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (chatList.isNotEmpty() && position < chatList.size) {
            val chat = chatList[position];
            holder.binding.tvName.text = chat.receiverNickname;
        }
    };

    inner class ViewHolder(var binding : ItemRecvMessageBinding) : RecyclerView.ViewHolder(
        binding.root
    );
};