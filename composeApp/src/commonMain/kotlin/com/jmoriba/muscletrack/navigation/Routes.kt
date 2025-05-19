package com.jmoriba.muscletrack.navigation

import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_home
import muscletrack.composeapp.generated.resources.ic_report
import muscletrack.composeapp.generated.resources.ic_pose
import muscletrack.composeapp.generated.resources.ic_setting
import org.jetbrains.compose.resources.DrawableResource

object Routes {
    const val LOGIN = "/login"
    const val HOME = "/home"
    const val DETAIL = "/detail/{id}"
    const val REPORT = "/report"
    const val DETECTION = "/detection"
    const val SETTINGS = "/settings"
}

data class NavigationItems(
    val icon: DrawableResource,
    val title: String,
    val route: String
)

val navigationItemsLists = listOf(
    NavigationItems(
        icon = Res.drawable.ic_home,
        title = "Home",
        route = Routes.HOME,
    ),
    NavigationItems(
        icon = Res.drawable.ic_report,
        title = "Report",
        route = Routes.REPORT,
    ),
    NavigationItems(
        icon = Res.drawable.ic_pose,
        title = "Pose Detection",
        route = Routes.DETECTION,
    ),
    NavigationItems(
        icon = Res.drawable.ic_setting,
        title = "Settings",
        route = Routes.SETTINGS,
    ),
)