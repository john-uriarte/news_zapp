package com.example.newszapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newszapp.R
import com.example.newszapp.data.News


@Composable
fun NewsListScreen(getNewsList: () -> List<News>, onClick: (Int) -> Unit) {
    val newsList = getNewsList()

    Text(
        text = stringResource(R.string.headlines),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
    LazyColumn() {
        Log.i("newslist", "items: " + newsList.toString())
        items(newsList) {
            NewsListItem(it.urlToImage, it.title ?: "", it.publishedAt ?: "", it.id, onClick)
        }
    }
}

