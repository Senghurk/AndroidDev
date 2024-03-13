package com.csx4109.fragmentdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.csx4109.fragmentdemo.databinding.FragmentABinding

class FragmentA: Fragment() {
//    private val view: FragmentABinding by lazy { FragmentABinding.inflate(layoutInflater) }
    private lateinit var view: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = FragmentABinding.inflate(inflater, container, false)

        return view.root
    }
}