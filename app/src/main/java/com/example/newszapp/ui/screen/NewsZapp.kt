package com.example.newszapp.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newszapp.ui.screen.navigation.NewsNavHost
import com.example.newszapp.ui.theme.NewsZappTheme

@Composable
fun NewsZapp() {

    NewsZappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewsNavHost()
        }
    }
}


