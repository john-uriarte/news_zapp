package com.example.newszapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsResponse (
    val status: String?,
    val totalResults : Int?,
    val articles: List<News>
)

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
)