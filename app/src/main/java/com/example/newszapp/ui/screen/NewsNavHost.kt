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
    callGetNewsList: () -> List<News>,
    callGetNewsById: (Int) -> News?,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NewsDestination.NEWSLIST.name,
        modifier = modifier
    ) {
        composable(route = NewsDestination.NEWSLIST.name) {
            Log.i("NewsNavHost", "call NewsListScreen")
            NewsListScreen(callGetNewsList) { id ->
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
            it.arguments?.let { it1 ->
                Log.i("NewsNavHost", "call newssinglescreen id: ${it1.getInt("id")}")
                NewsSingleScreen(callGetNewsById, it1.getInt("id"))
            }

        }
    }
}