package com.example.workmanager.manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.data.Synchronizer
import com.example.data.repository.OfflineImagesRepository
import com.example.data.repository.OfflineUserImagesRepository
import com.example.database.AbpDatabase
import com.example.network.AbpNetworkSource
import com.example.workmanager.DelegatingWorker
import com.example.workmanager.delegatedData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext


@HiltWorker
class AbpWorkManager @AssistedInject constructor(
    @Assisted appContext:Context,
    @Assisted workerParameters: WorkerParameters,
    private val offlineUserImagesRepository: OfflineUserImagesRepository
) :CoroutineWorker(
    appContext = appContext,
    workerParameters
),Synchronizer {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
           val result = async {
               offlineUserImagesRepository.sync()
            }
            result.await()
            if(result.isCompleted){

                Result.success()
            }else{
                Result.retry()
            }
    }

    companion object{
        val abpWorkRequest = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .setInputData(AbpWorkManager::class.delegatedData())
            .build()
    }
}