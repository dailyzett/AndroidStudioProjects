package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.R.string
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        composeTestRule.onNodeWithTagForStringId(
            string.navigation_bottom
        ).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
        //Navigation rail is displayed
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_rail
        ).assertExists()
    }
}