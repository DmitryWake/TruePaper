package com.ewake.truepaper.features.splash.presentation.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination
import com.ewake.truepaper.features.splash.presentation.SplashScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object SplashDestination : NavigationDestination {
    override val route: String = "splash_route"
    override val destination: String = "splash_destination"
    override val shouldShowBottomBar: Boolean = false
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.splashGraph(onNavigate: () -> Unit) {
    navigation(route = SplashDestination.route, startDestination = SplashDestination.destination) {
        composable(route = SplashDestination.destination) { SplashScreen(onNavigate) }
    }
}