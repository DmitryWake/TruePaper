package com.ewake.truepaper.features.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination
import com.ewake.truepaper.features.profile.presentation.ProfileScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object ProfileDestination : NavigationDestination {
    override val route: String = "profile_route"
    override val destination: String = "profile_destination"
    override val shouldShowBottomBar: Boolean = true
}

fun NavGraphBuilder.profileGraph() {
    navigation(
        route = ProfileDestination.route,
        startDestination = ProfileDestination.destination
    ) {
        composable(route = ProfileDestination.destination) { ProfileScreen() }
    }
}