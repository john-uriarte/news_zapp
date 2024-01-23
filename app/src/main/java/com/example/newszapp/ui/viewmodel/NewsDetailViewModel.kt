package com.example.newszapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newszapp.data.News
import com.example.newszapp.data.database.NewsDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NewsDetailViewModel"

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsDbRepository: NewsDbRepository
): ViewModel() {
    var news: MutableState<News?> = mutableStateOf(null)
        private set

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            Log.i(TAG, "getNewsById: $id")

            news.value = newsDbRepository.getNewsById(id)

            Log.i(TAG, "getNewsById: news: ${news.value}")
        }
    }
}