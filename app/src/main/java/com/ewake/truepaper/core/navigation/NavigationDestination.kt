package com.ewake.truepaper.core.navigation

/**
 * @author Nikolaevskiy Dmitriy
 */
interface NavigationDestination {
    val route: String
    val destination: String
    val shouldShowBottomBar: Boolean
}