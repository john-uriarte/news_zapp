package com.example.newszapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newszapp.data.News
import com.example.newszapp.data.database.NewsDbRepository
import kotlinx.coroutines.launch

private const val TAG = "NewsDetailViewModel"

class NewsDetailViewModel(app: Application): AndroidViewModel(app) {
    private val _news: MutableState<News?> = mutableStateOf(null)
    val news: State<News?>
        get() = _news

    private val newsDbRepository = NewsDbRepository(app)

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            Log.i(TAG, "getNewsById: $id")

            _news.value = newsDbRepository.getNewsById(id)

            Log.i(TAG, "getNewsById: news: ${_news.value}")
        }
    }
}