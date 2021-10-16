package com.onban.kauantapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onban.kauantapp.repo.Repository
import com.onban.network.data.NewsData
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private var _mainNewsList = MutableLiveData<List<NewsData>>()
    val mainNewsList: LiveData<List<NewsData>> = _mainNewsList

    fun fetchNews(pageNo: Int, company: String) {
        viewModelScope.launch {
            val res = repository.getCompanyNews(pageNo, company)
            if (res.isSuccessful) {
                _mainNewsList.value = res.body()?.newsList
                _mainNewsList.value?.get(0)?.let { Log.d("Test", it.title) }
            }
        }
    }
}