package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.csx4109.fragmentdemo.adapters.RecyclerViewAdapter
import com.csx4109.fragmentdemo.databinding.ActivityRecyclerViewBinding
import com.csx4109.fragmentdemo.models.UserScore

class RecyclerViewActivity : AppCompatActivity() {
    private val view: ActivityRecyclerViewBinding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }
    private val userScores = listOf(
        UserScore(123, "John", 100),
        UserScore(456, "Jane", 90),
        UserScore(789, "Joe", 80),
        UserScore(101, "Jill", 70),
        UserScore(112, "Jack", 60),
        UserScore(131, "Jenny", 50),
        UserScore(415, "Jared", 40),
        UserScore(161, "Jesse", 30),
        UserScore(718, "Jasmine", 20),
        UserScore(191, "Jared", 10),
        UserScore(110, "Jill", 1000),
        UserScore(111, "Jack", 1000)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        /*
        1. Assign data to item view
        2. Assign item view to recycler view
         */

        val divider = DividerItemDecoration(view.rvScoreBoard.context, 1)

        view.rvScoreBoard.addItemDecoration(divider)
        view.rvScoreBoard.adapter = RecyclerViewAdapter(this, userScores)
        view.rvScoreBoard.layoutManager = LinearLayoutManager(this)
//        view.rvScoreBoard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
//        view.rvScoreBoard.layoutManager = GridLayoutManager(this, 4)
    }
}