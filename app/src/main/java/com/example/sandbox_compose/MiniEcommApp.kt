package com.example.sandbox_compose

import android.app.Application
import com.example.sandbox_compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MiniEcommApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MiniEcommApp)
            modules(appModule)
        }
    }
}
