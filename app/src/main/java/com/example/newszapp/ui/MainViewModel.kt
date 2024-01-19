package com.example.newszapp.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newszapp.data.News
import com.example.newszapp.data.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel (app: Application) : AndroidViewModel(app) {
    private val _newsList: MutableState<List<News>> = mutableStateOf(emptyList())
    val newsList: State<List<News>>
        get() = _newsList

    private val _news: MutableState<News?> = mutableStateOf(null)
    val news: State<News?>
        get() = _news

    private val newsRepository = NewsRepository(app)

    init {
        Log.i("mainViewModel", "init")
        getNewsList()
    }

    fun getNewsList() {
        viewModelScope.launch {
            _newsList.value = newsRepository.getNews()
        }
    }

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            Log.i("mainViewModel", "getNewsById: $id")

            newsRepository.getNewsById(id)?.let {
                _news.value = it
            }
            //_news.value = newsRepository.getNewsById(id)
            //Thread.sleep(500)
            Log.i("mainViewModel", "getNewsById: news: ${_news.value}")

        }

    }
}