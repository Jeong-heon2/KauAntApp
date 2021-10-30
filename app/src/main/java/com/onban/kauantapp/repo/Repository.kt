package com.onban.kauantapp.repo

import com.onban.network.datasource.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCompanyNews(pageNo: Int, company: String) = remoteDataSource.getCompanyNews(pageNo, company)
    suspend fun getCompnayList() = remoteDataSource.getCompnayList()
}