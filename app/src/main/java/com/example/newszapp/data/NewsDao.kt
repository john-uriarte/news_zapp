package com.example.newszapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(news: List<News>)

    @Query("SELECT * FROM news WHERE content IS NOT NULL AND content != '[Removed]'")
    suspend fun getAllNews(): List<News>

    @Query("SELECT * FROM news WHERE id = :id")
    suspend fun getNewsById(id: Int): News

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()

}