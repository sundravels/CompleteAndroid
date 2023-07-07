package com.sundravels.androidbestpractices

import android.app.Application
import com.example.workmanager.initializers.AbpSync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
         AbpSync.initialize(this)
    }
}