package com.example.newszapp.ui.screen.list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newszapp.R
import com.example.newszapp.ui.screen.appbar.NewsBottomBar
import com.example.newszapp.ui.screen.appbar.NewsTopBar
import com.example.newszapp.ui.viewmodel.NewsListViewModel


private const val TAG = "NewsListScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navController: NavHostController,
    vm: NewsListViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val newsList = vm.newsList.value
    val isLoading = vm.isLoading.value
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val currentBackStack by navController.currentBackStackEntryAsState()


    Scaffold(
        topBar = {
            NewsTopBar(
                context,
                currentBackStack?.destination,
                scrollBehavior
            ) { navController.navigateUp() }
        },
        bottomBar = {
            NewsBottomBar(context, isLoading, currentBackStack?.destination) { vm.getNewsList() }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = stringResource(R.string.headlines),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
            )
            if (isLoading) {
                FullScreenLoading()
            } else {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                    state = scrollState
                ) {
                    Log.i(TAG, "items: " + newsList.toString())
                    items(newsList) { news ->
                        NewsListItem(
                            news.urlToImage,
                            news.title ?: "",
                            news.publishedAt ?: "",
                            news.id,
                            onClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}


