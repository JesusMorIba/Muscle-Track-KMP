package com.jmoriba.muscletrack

import androidx.compose.runtime.Composable
import com.jmoriba.muscletrack.designsystem.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp

@Composable
fun App() {
    PreComposeApp {
        AppTheme {
            MainScaffold()
        }
    }
}
