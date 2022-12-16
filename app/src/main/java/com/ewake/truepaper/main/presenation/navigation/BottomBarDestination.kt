package com.ewake.truepaper.main.presenation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination

/**
 * @author Nikolaevskiy Dmitriy
 */
data class BottomBarDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val iconRes: Int? = null,
    @StringRes val titleRes: Int
) : NavigationDestination {
    override val shouldShowBottomBar: Boolean = true
}