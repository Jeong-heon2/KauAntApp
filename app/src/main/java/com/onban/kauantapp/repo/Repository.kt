package com.onban.kauantapp.repo

import com.onban.network.datasource.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCompanyNews(pageNo: Int, company: String) = remoteDataSource.getCompanyNews(pageNo, company)
    suspend fun getCompanyList() = remoteDataSource.getCompanyList()
    suspend fun getAnalysisData(newsNo: String) = remoteDataSource.getAnalysisData(newsNo)
    suspend fun getSimilarityNews(newsNo: String, pageNo: Int) = remoteDataSource.getSimilarityNews(newsNo, pageNo)
}