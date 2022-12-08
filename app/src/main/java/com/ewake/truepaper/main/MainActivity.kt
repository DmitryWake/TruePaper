package com.ewake.truepaper.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.ewake.truepaper.features.feed.presentation.navigation.FeedDestination
import com.ewake.truepaper.main.ui.MainApp

class MainActivity : ComponentActivity() {
    @ExperimentalLifecycleComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(startNavigationDestination = FeedDestination)
        }
    }
}