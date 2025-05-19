package com.jmoriba.muscletrack

import androidx.compose.runtime.Composable
import com.jmoriba.muscletrack.designsystem.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinContext

@Composable
fun App() {
    PreComposeApp {
        KoinContext {
            AppTheme {
                MainScaffold()
            }
        }
    }
}
