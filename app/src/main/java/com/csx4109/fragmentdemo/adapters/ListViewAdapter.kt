package com.csx4109.fragmentdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.csx4109.fragmentdemo.databinding.ListViewItemBinding
import com.csx4109.fragmentdemo.models.UserScore

class ListViewAdapter(private val context: Context, private val userScores: List<UserScore>): BaseAdapter() {

    private class ViewHolder(binding: ListViewItemBinding) {
        val tvUserId = binding.tvUserId
        val tvUserName = binding.tvUserName
        val tvScore = binding.tvScore
    }

    override fun getCount(): Int = userScores.size

    override fun getItem(position: Int): Any = userScores[position]

    override fun getItemId(position: Int): Long = userScores[position].userId

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // old way
//        val userScore = userScores[position]
//        val binding = ListViewItemBinding.inflate(LayoutInflater.from(context), parent, false)
//
//        binding.tvUserId.text = userScore.userId.toString()
//        binding.tvUserName.text = userScore.userName
//        binding.tvScore.text = userScore.score.toString()
//
//        return binding.root

        // new way with ViewHolder pattern
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            val binding = ListViewItemBinding.inflate(LayoutInflater.from(context), parent, false)

            view = binding.root
            viewHolder = ViewHolder(binding)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val score = userScores[position]
        viewHolder.tvUserId.text = score.userId.toString()
        viewHolder.tvUserName.text = score.userName
        viewHolder.tvScore.text = score.score.toString()

        return view
    }
}