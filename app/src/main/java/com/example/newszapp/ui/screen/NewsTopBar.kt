package com.example.newszapp.ui.screen

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.newszapp.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewsTopBar(
    context: Context,
    destination: NavDestination?,
    scrollBehavior: TopAppBarScrollBehavior,
    onClickBack: () -> Unit
) {
    val notStartScreen = destination?.hierarchy?.any {
        it.route != null && it.route != NewsDestination.NEWSLIST.name
    }

    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(R.string.app_name))

        },
        navigationIcon = { if (notStartScreen == true) {
                //Log.i("NewsTopBar", "arrowback")
                IconButton(onClick = { onClickBack()  }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
                //Log.i("NewsTopBar", "appicon")
                IconButton(onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_newspaper_24),
                    contentDescription = "Localized description"
                )
            }
            }
        },
        actions = {
            IconButton(onClick = { showToastNotImplemented(context) }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}