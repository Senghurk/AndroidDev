package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csx4109.fragmentdemo.databinding.ActivityFragment2Binding
import com.csx4109.fragmentdemo.fragments.FragmentA
import com.csx4109.fragmentdemo.fragments.FragmentB
import com.csx4109.fragmentdemo.fragments.FragmentC

class Fragment2Activity : AppCompatActivity() {
    private val view: ActivityFragment2Binding by lazy { ActivityFragment2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnFragmentA.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcFragment2, FragmentA())
                .addToBackStack(FragmentA::class.java.name)
                .commit()
        }

        view.btnFragmentB.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcFragment2, FragmentB())
                .addToBackStack(FragmentB::class.java.name)
                .commit()
        }

        view.btnFragmentC.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcFragment2, FragmentC())
                .addToBackStack(FragmentC::class.java.name)
                .commit()
        }
    }
}