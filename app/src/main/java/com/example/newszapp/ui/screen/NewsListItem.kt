package com.example.newszapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListItem(
    imageUrl: String?,
    title: String,
    date: String,
    id: Int,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(id) },
        modifier = Modifier.padding(
            vertical = 4.dp, horizontal = 16.dp
        )
    ) {
        val modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        val formattedDate = getDateTimeFromUTC(date)
        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            imageUrl?.let {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    modifier = modifier
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
            )

            Text(text = formattedDate, modifier = modifier)
        }
    }

}

