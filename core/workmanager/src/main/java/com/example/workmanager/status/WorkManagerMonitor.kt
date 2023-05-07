package com.example.workmanager.status

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.data.monitor.SyncMonitor
import com.example.workmanager.initializers.workerName
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate


class WorkManagerMonitor(@ApplicationContext val context: Context):SyncMonitor {
    override val isSyncing: Flow<Boolean>
        get() = WorkManager.getInstance(context)
            .getWorkInfosForUniqueWorkLiveData(workerName)
            .map(kotlin.collections.MutableList<WorkInfo>::anyRunning)
            .asFlow()
            .conflate()
}

private val List<WorkInfo>.anyRunning get() = any {
    it.state == WorkInfo.State.RUNNING
}

