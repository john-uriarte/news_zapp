package com.example.newszapp.data.network

import android.util.Log
import com.example.newszapp.data.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://newsapi.org/v2/"
const val NEWS_API_KEY = "cc4178e3a141442f810d794a54cf1c9c"
const val NEWS_ARTICLE_COUNT = 20
const val NEWS_ARTICLE_COUNTRY = "us"

private const val TAG = "NewsApiRepository"

class NewsApiRepository() {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val newsApi: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }

    suspend fun getNews(): List<News> {
        Log.i(TAG,"getNews Start")

        try {
            val response = newsApi.getNews(NEWS_ARTICLE_COUNTRY, NEWS_API_KEY, NEWS_ARTICLE_COUNT)
            Log.i(TAG,"isSuccessful: ${response.isSuccessful}, code: ${response.code()}")
            if (response.isSuccessful) {
                Log.i("TAG", "length: " + response.body()?.totalResults)
                Log.i("TAG", "body: " + response.body().toString())

                response.body()?.articles?.let { newsList ->
                    return newsList
                }
            }
        } catch (e: Exception) {
            Log.i(TAG, "Error: " + e.message)
        }
        return emptyList()
    }
}