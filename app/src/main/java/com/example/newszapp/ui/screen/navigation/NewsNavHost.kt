package com.example.newszapp.ui.screen.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newszapp.ui.screen.detail.NewsDetailScreen
import com.example.newszapp.ui.screen.list.NewsListScreen

enum class NewsDestination {
    NEWSLIST,
    NEWSSINGLE
}

@Composable
fun NewsNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsDestination.NEWSLIST.name,
        modifier = Modifier
    ) {
        composable(route = NewsDestination.NEWSLIST.name) {
            Log.i("NewsNavHost", "call NewsListScreen")
            NewsListScreen (navController){ id ->
                navController.navigate(
                    "${NewsDestination.NEWSSINGLE.name}/${id}"
                )
            }
        }
        composable(
            route = "${NewsDestination.NEWSSINGLE.name}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.let { param ->
                Log.i("NewsNavHost", "call NewsDetailScreen id: ${param.getInt("id")}")
                NewsDetailScreen(navController, param.getInt("id"))
            }

        }
    }
}