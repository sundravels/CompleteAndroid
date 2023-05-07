package com.example.workmanager.initializers

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import com.example.workmanager.manager.AbpWorkManager

object AbpSync {

    fun initialize(context: Context) {
        AppInitializer.getInstance(context).initializeComponent(WorkInitializer::class.java)
    }
}

internal const val workerName = "Abp"

class WorkInitializer : Initializer<AbpSync> {
    override fun create(context: Context): AbpSync {
         WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                workerName,
                ExistingWorkPolicy.KEEP,
                AbpWorkManager.abpWorkRequest
            )
        }
        return AbpSync
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(WorkManagerInitializer::class.java)
    }
}