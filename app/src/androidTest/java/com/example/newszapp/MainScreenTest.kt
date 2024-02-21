package com.example.newszapp

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.newszapp.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {
    val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        Thread.sleep(2000)
    }

    @Test
    fun checkLabels() {
        composeTestRule.onNodeWithText(context.getString(R.string.app_name)).assertExists()
        composeTestRule.onNodeWithText(context.getString(R.string.headlines)).assertExists()
        composeTestRule.onNodeWithText(context.getString(R.string.refresh)).assertExists()
    }
}