package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csx4109.fragmentdemo.adapters.ListViewAdapter
import com.csx4109.fragmentdemo.databinding.ActivityListViewBinding
import com.csx4109.fragmentdemo.models.UserScore

class ListViewActivity : AppCompatActivity() {
    private val view: ActivityListViewBinding by lazy { ActivityListViewBinding.inflate(layoutInflater) }
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
        Game score board
        - userId
        - player name
        - score
        - isUser


        1. Assign data to item
        2. Assign item to listView
         */

        view.lvScoreBoard.adapter = ListViewAdapter(this, userScores)
    }
}