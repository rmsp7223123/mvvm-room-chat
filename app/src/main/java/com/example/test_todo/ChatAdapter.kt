package com.example.test_todo

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_todo.common.CommonVar
import com.example.test_todo.databinding.ItemRecvHomeBinding
import com.example.test_todo.databinding.ItemRecvMessageBinding
import com.example.test_todo.model.Chat
import java.text.SimpleDateFormat
import java.util.Date

class ChatAdapter(private var internalChatList: List<Chat>, private var context: Context) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecvMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        );
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
        holder.binding.containerFrame.removeAllViews();

        val chatTime = Date(chat.time);
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        val formattedTime = sdf.format(chatTime);

        val tvMsg = TextView(context);
        val tvTime = TextView(context);
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        val params2 = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );

        if (chat.senderId == CommonVar.user_id) {
            holder.binding.containerFrame.layoutParams = params2;
            holder.binding.tvName.visibility = View.GONE;
            params2.gravity = Gravity.END;
            tvMsg.text = chat.message;
            tvMsg.setTextColor(Color.parseColor("#000000"));
            tvMsg.textSize = 16f;
            tvMsg.setPadding(30, 20, 70, 20);
            tvMsg.maxWidth = 800;
            params.gravity = Gravity.BOTTOM or Gravity.END;
            params.setMargins(0, 0, 20, 0);
            tvTime.layoutParams = params;
            tvTime.text = formattedTime;
            tvTime.setTextColor(Color.parseColor("#000000"));
            tvTime.textSize = 12f;
            holder.binding.containerFrame.addView(tvTime);
            holder.binding.containerFrame.addView(tvMsg);
        } else {
            tvMsg.text = chat.message;
            tvMsg.setTextColor(Color.parseColor("#000000"));
            tvMsg.textSize = 16f;
            tvMsg.setPadding(30, 20, 70, 20);
            tvMsg.maxWidth = 800;
            params.gravity = Gravity.BOTTOM or Gravity.START;
            tvTime.layoutParams = params;
            tvTime.text = formattedTime;
            tvTime.setTextColor(Color.parseColor("#000000"));
            tvTime.textSize = 12f;
            holder.binding.containerFrame.addView(tvMsg);
            holder.binding.containerFrame.addView(tvTime);
        }
    };

    inner class ViewHolder(var binding: ItemRecvMessageBinding) : RecyclerView.ViewHolder(
        binding.root
    );
};