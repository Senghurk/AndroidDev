package com.csx4109.fragmentdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.csx4109.fragmentdemo.databinding.FragmentBBinding

class FragmentB: Fragment() {
    private lateinit var view: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentBBinding.inflate(inflater, container, false)

        return view.root
    }
}