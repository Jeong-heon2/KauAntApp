package com.onban.kauantapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onban.kauantapp.repo.Repository
import com.onban.network.data.CompanyEntity
import com.onban.network.data.NetworkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private var _companyList = MutableLiveData<List<CompanyEntity>>()
    val companyList: LiveData<List<CompanyEntity>> = _companyList

    fun fetchCompanyList() {
        viewModelScope.launch {
            when(val res = repository.getCompnayList()) {
                is NetworkResponse.Success -> {
                    _companyList.value = res.body.result
                }
            }
        }
    }
}