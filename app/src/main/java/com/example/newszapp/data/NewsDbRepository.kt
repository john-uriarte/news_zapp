package com.example.newszapp.data

import android.app.Application
import android.util.Log

private const val TAG = "NewsDbRepository"

class NewsDbRepository(app: Application) {
    private val newsDao = NewsDatabase.getDatabase(app)
        .newsDao()

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