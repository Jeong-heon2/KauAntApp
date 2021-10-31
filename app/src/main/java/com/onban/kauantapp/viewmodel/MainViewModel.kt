package com.onban.kauantapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onban.kauantapp.repo.Repository
import com.onban.network.data.NetworkResponse
import com.onban.network.data.NewsData
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private var _mainNewsList = MutableLiveData<List<NewsData>>()
    val mainNewsList: LiveData<List<NewsData>> = _mainNewsList
    private var mainNewsPageNo = 0

    fun fetchNextNews(companyName: String) {
        viewModelScope.launch {
            // company는 나중에 변수로 변경해야 함
            when (val res = repository.getCompanyNews(mainNewsPageNo++, companyName)) {
                is NetworkResponse.Success -> {
                    _mainNewsList.value = res.body.newsList
                }
            }
        }
    }
}