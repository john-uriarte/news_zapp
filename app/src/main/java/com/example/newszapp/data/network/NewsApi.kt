package com.example.newszapp.data.network

import com.example.newszapp.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") page: Int
    ): Response<NewsResponse>
}