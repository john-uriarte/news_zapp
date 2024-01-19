package com.example.newszapp.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newszapp.data.News
import com.example.newszapp.data.NewsApiRepository
import com.example.newszapp.data.NewsDbRepository
import kotlinx.coroutines.launch

private const val TAG = "mainViewModel"

class MainViewModel (app: Application) : AndroidViewModel(app) {
    private val _newsList: MutableState<List<News>> = mutableStateOf(emptyList())
    val newsList: State<List<News>>
        get() = _newsList

    private val _news: MutableState<News?> = mutableStateOf(null)
    val news: State<News?>
        get() = _news

    private val newsApiRepository = NewsApiRepository()
    private val newsDbRepository = NewsDbRepository(app)

    init {
        Log.i(TAG, "init")
        getNewsList()
    }

    fun getNewsList() {
        viewModelScope.launch {
            val newsList = newsApiRepository.getNews()
            if (newsList.isNotEmpty()) {
                newsDbRepository.deleteAllNews()
                newsDbRepository.storeNewsInDb(newsList)
            }
            _newsList.value = newsDbRepository.getAllNewsFromDb()
        }
    }

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            Log.i(TAG, "getNewsById: $id")

            _news.value = newsDbRepository.getNewsById(id)

            Log.i(TAG, "getNewsById: news: ${_news.value}")
        }

    }
}