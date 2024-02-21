package com.example.newszapp.ui.screen.appbar

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.newszapp.R
import com.example.newszapp.ui.screen.navigation.NewsDestination
import com.example.newszapp.ui.screen.showToastNotImplemented

@Composable
fun NewsBottomBar(
    context: Context,
    isLoading: Boolean,
    destination: NavDestination?,
    getNewsList: () -> Unit
) {
    val notStartScreen = destination?.hierarchy?.any {
        it.route != null && it.route != NewsDestination.NEWSLIST.name
    }

    BottomAppBar(
        actions = {
            IconButton(onClick = { showToastNotImplemented(context) }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = ""
                )
            }
        },
        floatingActionButton = {
            if (notStartScreen == false && !isLoading) {
                FloatingActionButton(
                    onClick = { getNewsList() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Text(
                        text = stringResource(R.string.refresh),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            } else {
                /* Do nothing */
            }
        }
    )
}