package com.csx4109.fragmentdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csx4109.fragmentdemo.databinding.ListViewItemBinding
import com.csx4109.fragmentdemo.models.UserScore

class RecyclerViewAdapter(private val context: Context, private val userScores: List<UserScore>)
    : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        class RecyclerViewHolder(val binding: ListViewItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ListViewItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = userScores.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val userScore = userScores[position]

        holder.binding.tvUserId.text = userScore.userId.toString()
        holder.binding.tvUserName.text = userScore.userName
        holder.binding.tvScore.text = userScore.score.toString()
    }
}