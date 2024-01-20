package com.example.newszapp

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.newszapp.ui.screen.NewsZapp
import com.example.newszapp.ui.viewmodel.NewsListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val testViewModel = NewsListViewModel(ApplicationProvider.getApplicationContext())

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        rule.setContent {
            NewsZapp(testViewModel)
        }
        Thread.sleep(2000)
    }

    @Test
    fun checkLabels() {
        rule.onNodeWithText(context.getString(R.string.app_name)).assertExists()
        rule.onNodeWithText(context.getString(R.string.headlines)).assertExists()
        rule.onNodeWithText(context.getString(R.string.refresh)).assertExists()
    }
}