package com.example.newszapp.data.network

import android.util.Log
import com.example.newszapp.data.News
import javax.inject.Inject
import javax.inject.Singleton

private const val NEWS_API_KEY = "cc4178e3a141442f810d794a54cf1c9c"
private const val NEWS_ARTICLE_COUNT = 20
private const val NEWS_ARTICLE_COUNTRY = "us"

private const val TAG = "NewsApiRepository"

@Singleton
class NewsApiRepository @Inject constructor(
    private val newsApi: NewsApi) {

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