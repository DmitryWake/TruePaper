package com.ewake.truepaper.features.feed.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewake.truepaper.core.navigation.NavigationDestination
import com.ewake.truepaper.features.feed.presentation.FeedScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object FeedDestination : NavigationDestination {
    override val route: String = "feed_route"
    override val destination: String = "feed_destination"
    override val shouldShowBottomBar: Boolean = true
}

fun NavGraphBuilder.feedGraph() {
    navigation(route = FeedDestination.route, startDestination = FeedDestination.destination) {
        composable(route = FeedDestination.destination) { FeedScreen() }
    }
}