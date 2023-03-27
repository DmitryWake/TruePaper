package com.ewake.truepaper.main.presenation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ewake.truepaper.features.splash.presentation.navigation.SplashDestination
import com.ewake.truepaper.main.presenation.ui.MainApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(startNavigationDestination = SplashDestination)
        }
    }
}