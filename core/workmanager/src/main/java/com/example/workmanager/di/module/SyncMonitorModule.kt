package com.example.workmanager.di.module

import com.example.data.monitor.SyncMonitor
import com.example.workmanager.status.WorkManagerMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface SyncMonitorModule{

    @Binds
    fun getSyncModule(workManagerMonitor: WorkManagerMonitor):SyncMonitor

}


