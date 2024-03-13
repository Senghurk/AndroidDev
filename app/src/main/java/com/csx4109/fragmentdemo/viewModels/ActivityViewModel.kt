package com.csx4109.fragmentdemo.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csx4109.fragmentdemo.data.api.ActivityApi
import com.csx4109.fragmentdemo.models.ActivityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel: ViewModel() {
    private val api = ActivityApi()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val activity: MutableLiveData<ActivityResponse> = MutableLiveData()

    fun getActivity() {
        showLoading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getActivity()

            activity.postValue(response)
            dismissLoading()
        }
    }

    fun showLoading() {
        isLoading.postValue(true)
    }

    fun dismissLoading() {
        isLoading.postValue(false)
    }
}