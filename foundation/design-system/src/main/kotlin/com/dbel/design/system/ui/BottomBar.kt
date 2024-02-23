package com.dbel.design.system.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavController,
    screens: List<Screen>,
    modifier: Modifier = Modifier
) = BottomNavigation(
    backgroundColor = MaterialTheme.colorScheme.primary,
    modifier = modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    screens.forEach { screen -> BottomBarItem(
        screen,
        navController,
        isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    ) }
}

@Composable
fun RowScope.BottomBarItem(
    screen: Screen,
    navController: NavController,
    isSelected: Boolean,
    contentColor: Color = BottomBarItemDefaults.contentColor(isSelected)
) = BottomNavigationItem(
    icon = {
        Icon(
            contentDescription = null,
            painter = painterResource(id = screen.iconId),
            tint = contentColor
        )
    },
    label = { Text(text = stringResource(screen.resourceId), color = contentColor) },
    selected = isSelected,
    onClick = {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
)

object BottomBarItemDefaults {

    @Composable
    fun contentColor(isSelected: Boolean) = when (isSelected) {
        true -> MaterialTheme.colorScheme.onPrimary
        false -> MaterialTheme.colorScheme.inversePrimary
    }
}

class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int)
