package com.jmoriba.muscletrack.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import com.jmoriba.muscletrack.designsystem.component.navigation.NavigationItem

object Routes {
    const val HOME = "/home"
    const val DETAIL = "/detail/{id}"
    const val REPORT = "/report"
    const val DETECTION = "/detection"
    const val SETTINGS = "/settings"
}

val navigationItemsLists = listOf(
    NavigationItem(
        icon = Icons.Outlined.Home,
        title = "Home",
        route = Routes.HOME,
    ),
    NavigationItem(
        icon = Icons.Outlined.Search,
        title = "Report",
        route = Routes.REPORT,
    ),
    NavigationItem(
        icon = Icons.Outlined.Search,
        title = "Pose Detection",
        route = Routes.DETECTION,
    ),
    NavigationItem(
        icon = Icons.Outlined.Search,
        title = "Settings",
        route = Routes.SETTINGS,
    ),
)