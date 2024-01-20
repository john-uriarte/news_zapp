package com.example.newszapp

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.newszapp.ui.screen.NewsZapp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        rule.setContent {
            NewsZapp()
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