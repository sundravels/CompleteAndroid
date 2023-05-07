package com.sundravels.androidbestpractices

import android.app.Application
import android.content.res.Configuration
import androidx.hilt.work.HiltWorkerFactory
import com.example.workmanager.initializers.AbpSync
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
         AbpSync.initialize(this)
    }
}