package com.onban.network.api

import com.onban.network.data.CompanyNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("news?")
    suspend fun getNews(
        @Query("pageNo") pageNo: Int,
        @Query("company") company: String
    ): Response<CompanyNewsResponse>
}