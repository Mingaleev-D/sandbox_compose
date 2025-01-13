package com.example.sandbox_compose

import android.app.Application

class GeminiApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}
