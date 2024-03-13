package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.csx4109.fragmentdemo.adapters.RecyclerViewAdapter
import com.csx4109.fragmentdemo.databinding.ActivitySearchableRecyclerBinding
import com.csx4109.fragmentdemo.models.UserScore

class SearchableRecyclerActivity : AppCompatActivity() {
    private val view: ActivitySearchableRecyclerBinding by lazy { ActivitySearchableRecyclerBinding.inflate(layoutInflater) }
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
    private val filteredUserScore = mutableListOf<UserScore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        filteredUserScore.addAll(userScores)
        view.rvSearchable.adapter = RecyclerViewAdapter(this, filteredUserScore)
        view.rvSearchable.layoutManager = LinearLayoutManager(this)

        view.svSearchable.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                filterData(query)
//                return true
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText)
                return true
            }

        })
    }

    private fun filterData(query: String?) {
        if(!query.isNullOrBlank()) {
            filteredUserScore.clear()
            filteredUserScore.addAll(userScores.filter { it.userName.contains(query, true) })
            view.rvSearchable.adapter?.notifyDataSetChanged()
        }
    }
}