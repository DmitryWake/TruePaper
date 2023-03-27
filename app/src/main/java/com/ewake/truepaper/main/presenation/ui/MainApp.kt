package com.ewake.truepaper.main.presenation.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ewake.truepaper.core.presentation.navigation.NavigationDestination
import com.ewake.truepaper.core.presentation.theme.WhereMyPetTheme
import com.ewake.truepaper.main.presenation.navigation.BottomBarDestination
import com.ewake.truepaper.main.presenation.navigation.NavHost

/**
 * @author Nikolaevskiy Dmitriy
 */
@Composable
fun MainApp(
    appState: AppState = rememberAppState(),
    startNavigationDestination: NavigationDestination
) {
    appState.isBottomBarVisible.value = startNavigationDestination.shouldShowBottomBar

    WhereMyPetTheme {
        Scaffold(
            bottomBar = {
                if (appState.isBottomBarVisible.value) {
                    MainBottomBar(
                        navController = appState.navController,
                        bottomDestination = appState.bottomNavigationDestinations,
                        onNavigate = {
                            appState.navigate(it)
                        }
                    )
                }
            }
        ) { padding ->
            NavHost(
                navController = appState.navController,
                onNavigate = appState::navigate,
                onBackPressed = appState::onBackPressed,
                padding,
                startNavigationDestination
            )
        }
    }
}

@Composable
fun MainBottomBar(
    navController: NavHostController,
    bottomDestination: List<BottomBarDestination>,
    onNavigate: (NavigationDestination) -> Unit
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomDestination.forEach { destination ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(destination.titleRes)) },
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = {
                    onNavigate.invoke(destination)
                }
            )
        }
    }
}