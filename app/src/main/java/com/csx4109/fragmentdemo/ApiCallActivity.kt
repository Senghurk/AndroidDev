package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.csx4109.fragmentdemo.data.api.ActivityApi
import com.csx4109.fragmentdemo.databinding.ActivityApiCallBinding
import com.csx4109.fragmentdemo.viewModels.ActivityViewModel
import kotlinx.coroutines.launch

class ApiCallActivity : AppCompatActivity() {
    private val view: ActivityApiCallBinding by lazy { ActivityApiCallBinding.inflate(layoutInflater) }
    private val viewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnRandom.setOnClickListener {
            viewModel.getActivity()
        }

        // Observer
        viewModel.activity.observe(this) { response ->
            view.tvResponse.text = response.activity
        }

        viewModel.isLoading.observe(this) {
            if (it) {
                view.progressBar.visibility = View.VISIBLE
                view.btnRandom.isClickable = false
            } else {
                view.progressBar.visibility = View.GONE
                view.btnRandom.isClickable = true
            }
        }
    }
}