package com.csx4109.fragmentdemo.viewModels

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csx4109.fragmentdemo.data.api.UniversityApi
import com.csx4109.fragmentdemo.data.cache.UniversityCache
import com.csx4109.fragmentdemo.models.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UniversityViewModel(app: Application): AndroidViewModel(app) {
    private val api = UniversityApi()
    private val cache = UniversityCache(app)

    val universities: MutableLiveData<List<University>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getUniversities() {
        showLoading()

        if (cache.hasUniversities()) {
            Log.d("OAT", "Using cache")
            val response = cache.getUniversities()
            universities.postValue(response)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d("OAT", "Calling API")
                val response = api.getUniversities()
                universities.postValue(response)
                cache.setUniversities(response)
            }
        }
        dismissLoading()
    }

    private fun showLoading() {
        isLoading.postValue(true)
    }

    private fun dismissLoading() {
        isLoading.postValue(false)
    }
}