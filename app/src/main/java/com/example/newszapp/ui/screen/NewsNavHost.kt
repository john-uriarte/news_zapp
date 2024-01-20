package com.example.newszapp.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newszapp.data.News

enum class NewsDestination {
    NEWSLIST,
    NEWSSINGLE
}

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NewsDestination.NEWSLIST.name,
        modifier = modifier
    ) {
        composable(route = NewsDestination.NEWSLIST.name) {
            Log.i("NewsNavHost", "call NewsListScreen")
            NewsListScreen { id ->
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
                Log.i("NewsNavHost", "call NewsSingleScreen id: ${param.getInt("id")}")
                NewsSingleScreen(param.getInt("id"))
            }

        }
    }
}