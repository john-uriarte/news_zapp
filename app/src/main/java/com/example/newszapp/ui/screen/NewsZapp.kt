package com.example.newszapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newszapp.ui.NewsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsZapp() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            NewsTopBar(
                context,
                currentBackStack?.destination,
                scrollBehavior,
                { navController.navigateUp() })
        },
        bottomBar = {
            NewsBottomBar(context, currentBackStack?.destination, {  })
        }
    ) {
        NewsNavHost(
            navController,
            Modifier.padding(it))
    }
}


