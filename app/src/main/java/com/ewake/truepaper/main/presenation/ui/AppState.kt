package com.ewake.truepaper.main.presenation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ewake.truepaper.R
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination
import com.ewake.truepaper.features.feed.presentation.navigation.FeedDestination
import com.ewake.truepaper.features.profile.presentation.navigation.ProfileDestination
import com.ewake.truepaper.main.presenation.navigation.BottomBarDestination

/**
 * @author Nikolaevskiy Dmitriy
 */
class AppState(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    var isBottomBarVisible: MutableState<Boolean> = mutableStateOf(true)

    val bottomNavigationDestinations = listOf(
        BottomBarDestination(
            route = FeedDestination.route,
            destination = FeedDestination.destination,
            iconRes = null,
            titleRes = R.string.feed_bottom_bar_title
        ),
        BottomBarDestination(
            route = ProfileDestination.route,
            destination = ProfileDestination.destination,
            iconRes = null,
            titleRes = R.string.profile_bottom_bar_title
        )
    )

    fun navigate(destination: NavigationDestination, route: String? = null) {
        if (destination is BottomBarDestination || bottomNavigationDestinations.firstOrNull { it.route == destination.route } != null) {
            navController.navigate(route ?: destination.destination) {
                val startDestination = navController.graph.findStartDestination()
                popUpTo(startDestination.id) {
                    saveState = true
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(route ?: destination.route)
        }

        isBottomBarVisible.value = destination.shouldShowBottomBar
    }

    fun onBackPressed(shouldShowBottomBar: Boolean = true) {
        navController.popBackStack()
        isBottomBarVisible.value = shouldShowBottomBar
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController) {
        AppState(navController)
    }
}