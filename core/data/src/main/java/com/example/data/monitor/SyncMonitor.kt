package com.example.data.monitor

import kotlinx.coroutines.flow.Flow

interface SyncMonitor {
    val isSyncing:Flow<Boolean>
}