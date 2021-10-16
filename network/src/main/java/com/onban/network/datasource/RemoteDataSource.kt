package com.onban.network.datasource

import com.onban.network.api.NewsApi

class RemoteDataSource(
    private val newsApi: NewsApi
) {
    suspend fun getCompanyNews(pageNo: Int, company: String) = newsApi.getNews(pageNo, company)
}