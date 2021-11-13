package com.onban.network.api

import com.onban.network.data.AnalysisNewsResponse
import com.onban.network.data.CompanyNewsResponse
import com.onban.network.data.CompanyResponse
import com.onban.network.data.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.Error

interface NewsApi {
    @GET("news?")
    suspend fun getNews(
        @Query("pageNo") pageNo: Int,
        @Query("company") company: String
    ): NetworkResponse<CompanyNewsResponse, Error>

    @GET("company")
    suspend fun getCompanyList(): NetworkResponse<CompanyResponse, Error>

    @GET("/news/{newsNo}")
    suspend fun getAnalysisData(@Path("newsNo") newsNo: String): NetworkResponse<AnalysisNewsResponse, Error>
}