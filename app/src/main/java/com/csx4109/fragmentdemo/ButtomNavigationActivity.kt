package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.csx4109.fragmentdemo.databinding.ActivityButtomNavigationBinding
import com.csx4109.fragmentdemo.fragments.FragmentA
import com.csx4109.fragmentdemo.fragments.FragmentB
import com.csx4109.fragmentdemo.fragments.FragmentC

class ButtomNavigationActivity : AppCompatActivity() {
    private val view: ActivityButtomNavigationBinding by lazy {
        ActivityButtomNavigationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.bnvExample.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> changeFragment(FragmentA())
                R.id.item_profile -> changeFragment(FragmentB())
                R.id.item_setting -> changeFragment(FragmentC())
                else -> false
            }
        }
    }

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcExample, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()


        return true
    }
}