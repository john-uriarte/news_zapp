package com.example.newszapp.data

import android.app.Application
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://newsapi.org/v2/"
const val NEWS_API_KEY = "cc4178e3a141442f810d794a54cf1c9c"
const val NEWS_ARTICLE_COUNT = 20
const val NEWS_ARTICLE_COUNTRY = "us"

class NewsRepository(app: Application) {
    private val newsDao = NewsDatabase.getDatabase(app)
        .newsDao()

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
        Log.i("NewsRepository","getNews Start")

        try {
            val response = newsApi.getNews(NEWS_ARTICLE_COUNTRY, NEWS_API_KEY, NEWS_ARTICLE_COUNT)
            Log.i("NewsRepository","isSuccessful: ${response.isSuccessful}, code: ${response.code()}")
            if (response.isSuccessful) {
                Log.i("NewsRepository", "length: " + response.body()?.totalResults)
                Log.i("NewsRepository", "body: " + response.body().toString())

                response.body()?.articles?.let {
                    deleteAllNews()
                    storeNewsInDb(response.body()?.articles)
                }
            }
        } catch (e: Exception) {
            Log.i("NewsRepository", "Error: " + e.message)
        }

        return getAllNewsFromDb()
    }

    private suspend fun storeNewsInDb(news: List<News>?) {
        if (news != null) {
            Log.i("NewsRepository", "storeNewsInDb: ")
            newsDao.insertNews(news)
        }
    }

    private suspend fun getAllNewsFromDb(): List<News> {
        return newsDao.getAllNews()
    }

    private suspend fun deleteAllNews() {
        Log.i("NewsRepository", "deleteAllNews: ")
        return newsDao.deleteAllNews()
    }

    suspend fun getNewsById(id: Int): News {
        Log.i("NewsRepository", "getNewsById: ")
        return newsDao.getNewsById(id)
    }
}