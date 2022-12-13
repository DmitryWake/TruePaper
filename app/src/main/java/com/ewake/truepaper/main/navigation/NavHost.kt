package com.ewake.truepaper.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination
import com.ewake.truepaper.features.feed.presentation.navigation.feedGraph

/**
 * @author Nikolaevskiy Dmitriy
 */
@ExperimentalLifecycleComposeApi
@Composable
fun NavHost(
    navController: NavHostController,
    onNavigate: (NavigationDestination, String?) -> Unit,
    onBackPressed: (Boolean) -> Unit,
    innerPadding: PaddingValues,
    startDestination: NavigationDestination
) {
    androidx.navigation.compose.NavHost(
        navController,
        startDestination = startDestination.route,
        Modifier.padding(innerPadding)
    ) {
        feedGraph()
    }
}