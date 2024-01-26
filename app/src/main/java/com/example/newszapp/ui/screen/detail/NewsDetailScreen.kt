package com.example.newszapp.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.newszapp.ui.screen.appbar.NewsBottomBar
import com.example.newszapp.ui.screen.appbar.NewsTopBar
import com.example.newszapp.ui.screen.getDateTimeFromUTC
import com.example.newszapp.ui.viewmodel.NewsDetailViewModel

private const val TAG = "NewsDetailScreen"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    navController: NavHostController,
    id: Int,
    vm: NewsDetailViewModel = hiltViewModel()
) {

    vm.getNewsById(id)
    val news = vm.news.value
    Log.i(TAG, "news: $news")

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
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
            NewsBottomBar(context, currentBackStack?.destination, { })
        }
    ) {
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(it)) {
            Card(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
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
    }
}