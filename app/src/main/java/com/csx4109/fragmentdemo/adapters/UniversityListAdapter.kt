package com.csx4109.fragmentdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csx4109.fragmentdemo.databinding.ItemUniversityBinding
import com.csx4109.fragmentdemo.models.University

class UniversityListAdapter(private val universities: List<University>) :
    RecyclerView.Adapter<UniversityListAdapter.UniversityViewHolder>() {

    class UniversityViewHolder(val binding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUniversityBinding.inflate(layoutInflater, parent, false)

        return UniversityViewHolder(binding)
    }

    override fun getItemCount(): Int = universities.size

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = universities[position]

        holder.binding.tvUniversityName.text = university.name
        holder.binding.tvUniversityProvince.text = university.`state-province`
    }
}