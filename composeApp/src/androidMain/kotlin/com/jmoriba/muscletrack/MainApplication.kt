package com.jmoriba.muscletrack

import android.app.Application
import com.jmoriba.muscletrack.di.appModule
import com.jmoriba.muscletrack.di.supabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule(), supabaseModule())
        }
    }
}
