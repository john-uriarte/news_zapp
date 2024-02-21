package com.example.newszapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newszapp.data.News
import com.example.newszapp.data.database.NewsDbRepository
import com.example.newszapp.data.network.NewsApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NewsListViewModel"

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsApiRepository: NewsApiRepository,
    private val newsDbRepository: NewsDbRepository
) : ViewModel() {
    var newsList: MutableState<List<News>> = mutableStateOf(emptyList())
        private set
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set
    init {
        Log.i(TAG, "init")
        getNewsList()
    }

    fun getNewsList() {
        viewModelScope.launch {
            isLoading.value = true
            val _newsList = newsApiRepository.getNews()
            if (_newsList.isNotEmpty()) {
                newsDbRepository.deleteAllNews()
                newsDbRepository.storeNewsInDb(_newsList)
            }

            newsList.value = newsDbRepository.getAllNewsFromDb()
            isLoading.value = false
        }
    }
}