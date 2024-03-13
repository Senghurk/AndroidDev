package com.csx4109.fragmentdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.csx4109.fragmentdemo.databinding.FragmentCBinding

class FragmentC: Fragment() {
    private lateinit var view: FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentCBinding.inflate(inflater, container, false)

        return view.root
    }
}