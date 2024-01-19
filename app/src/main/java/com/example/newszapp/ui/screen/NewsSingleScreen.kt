package com.example.newszapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newszapp.data.News

@Composable
fun NewsSingleScreen(callGetNewsById: (Int) -> News?, id: Int) {

    val news = callGetNewsById(id)
    Log.i("NewsSingleScreen", "news: $news")
    Card(modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            val modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
            news?.let {
                with(news) {
                    urlToImage?.let {
                        AsyncImage(
                            model = urlToImage,
                            contentDescription = "",
                            modifier = modifier
                        )
                    }

                    title?.let {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                        )
                    }

                    publishedAt?.let {
                        val formattedDate = getDateTimeFromUTC(publishedAt)
                        Text(
                            text = formattedDate,
                            modifier = modifier
                        )
                    }

                    author?.let {
                        Text(
                            text = "By $author",
                            modifier = modifier
                        )
                    }

                    content?.let {
                        Text(
                            text = content,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}