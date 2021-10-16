package com.onban.network.datasource

import com.onban.network.api.NewsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getCompanyNews(pageNo: Int, company: String) = newsApi.getNews(pageNo, company)
}