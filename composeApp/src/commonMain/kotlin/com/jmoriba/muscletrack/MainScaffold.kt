package com.jmoriba.muscletrack

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.jmoriba.muscletrack.designsystem.component.navigation.NavigationBar
import com.jmoriba.muscletrack.navigation.Navigation
import moe.tlaster.precompose.navigation.rememberNavigator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    val navigator = rememberNavigator()

    Scaffold(
        bottomBar = {
            NavigationBar(navigator = navigator)
        }
    ) { innerPadding ->
        Navigation(navigator)
    }
}
