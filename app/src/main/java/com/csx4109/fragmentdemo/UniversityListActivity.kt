package com.csx4109.fragmentdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.csx4109.fragmentdemo.adapters.UniversityListAdapter
import com.csx4109.fragmentdemo.data.api.UniversityApi
import com.csx4109.fragmentdemo.databinding.ActivityUniversityListBinding
import com.csx4109.fragmentdemo.models.University
import com.csx4109.fragmentdemo.viewModels.UniversityViewModel
import kotlinx.coroutines.launch

class UniversityListActivity : AppCompatActivity() {
    private val view: ActivityUniversityListBinding by lazy { ActivityUniversityListBinding.inflate(layoutInflater) }
    private val viewModel: UniversityViewModel by viewModels()
    private val universities: MutableList<University> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        viewModel.getUniversities()

        view.rvUniversities.adapter = UniversityListAdapter(universities)
        view.rvUniversities.layoutManager = LinearLayoutManager(this)
        view.swipeLayout.setOnRefreshListener {
            viewModel.getUniversities()
        }

        viewModel.universities.observe(this) { universityList ->
            universities.clear()
            universities.addAll(universityList)
            view.rvUniversities.adapter?.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            view.swipeLayout.isRefreshing = isLoading
        }
    }
}