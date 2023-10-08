package com.example.test_todo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.databinding.ItemRecvMessageBinding
import com.example.test_todo.model.Chat

class ChatAdapter(var internalChatList : List<Chat>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecvMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    };

    override fun getItemCount(): Int {
        return internalChatList.size;
    };

    init {
        Log.d("ChatAdapter", "Data size: ${internalChatList.size}")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val chat = internalChatList[position];
            holder.binding.tvName.text = chat.receiverNickname;
    };

    inner class ViewHolder(var binding : ItemRecvMessageBinding) : RecyclerView.ViewHolder(
        binding.root
    );
};