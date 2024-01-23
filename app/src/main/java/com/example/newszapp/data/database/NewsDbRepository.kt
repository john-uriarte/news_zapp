package com.example.newszapp.data.database

import android.util.Log
import com.example.newszapp.data.News
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "NewsDbRepository"
@Singleton
class NewsDbRepository @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun storeNewsInDb(news: List<News>?) {
        if (news != null) {
            Log.i(TAG, "storeNewsInDb: ")
            newsDao.insertNews(news)
        }
    }

    suspend fun getAllNewsFromDb(): List<News> {
        return newsDao.getAllNews()
    }

    suspend fun deleteAllNews() {
        Log.i(TAG, "deleteAllNews: ")
        return newsDao.deleteAllNews()
    }

    suspend fun getNewsById(id: Int): News {
        Log.i(TAG, "getNewsById: ")
        return newsDao.getNewsById(id)
    }
}