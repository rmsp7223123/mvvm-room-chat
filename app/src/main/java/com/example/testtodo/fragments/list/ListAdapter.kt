package com.example.testtodo.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_todo2.databinding.CustomRowBinding
import com.example.kotlin_todo2.fragments.list.ListFragmentDirections
import com.example.testtodo.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>() //

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    }

    override fun getItemCount(): Int {
        return userList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position];
        holder.binding.idTxt.text = currentItem.id.toString();
        holder.binding.firstNameTxt.text = currentItem.name;
        holder.binding.lastNameTxt.text = currentItem.name;
        holder.binding.ageTxt.text = currentItem.age.toString();

        holder.binding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem);
            // ListFragment에서 UpdateFragment로 이동하기 위한 동작을 정의한 NavDirections 객체를 생성
            holder.itemView.findNavController().navigate(action);
            // 사용자가 아이템을 클릭하면 해당 아이템의 정보를 가지고 UpdateFragment로 이동
        }
    }

    fun setData(user:List<User>){
        //유저리스트가 변경 되었을때, 업데이트
        this.userList = user;
        notifyDataSetChanged();
    };

    inner class MyViewHolder(var binding: CustomRowBinding):RecyclerView.ViewHolder(binding.root){};




}