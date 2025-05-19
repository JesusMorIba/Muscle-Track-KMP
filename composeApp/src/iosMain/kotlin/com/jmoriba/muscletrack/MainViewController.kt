package com.jmoriba.muscletrack

import androidx.compose.ui.window.ComposeUIViewController
import com.jmoriba.muscletrack.di.appModule
import com.jmoriba.muscletrack.di.supabaseModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(appModule(), supabaseModule())
    }.koin
}