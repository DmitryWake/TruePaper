package com.ewake.truepaper.features.splash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewake.truepaper.R
import com.ewake.truepaper.features.splash.presentation.viewmodel.SplashScreenUIState
import com.ewake.truepaper.features.splash.presentation.viewmodel.SplashViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */


@ExperimentalLifecycleComposeApi
@Composable
fun SplashScreen(
    onNavigate: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState: SplashScreenUIState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.init()

    when (uiState) {
        SplashScreenUIState.Loading -> SplashLogo()
        is SplashScreenUIState.Navigating -> onNavigate.invoke()
    }
}

@Composable
private fun SplashLogo() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(108.dp)
        )
    }
}