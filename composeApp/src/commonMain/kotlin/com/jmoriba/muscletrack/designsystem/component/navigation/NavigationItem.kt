package com.jmoriba.muscletrack.designsystem.component.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: String
)