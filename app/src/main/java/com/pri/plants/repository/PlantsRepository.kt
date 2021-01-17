package com.pri.plants.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pri.plants.api.ApiService
import com.pri.plants.data.Plant
import com.pri.plants.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlantsRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {
    fun loadPlantList(): Flow<PagingData<Plant>> {
        return Pager(
            config = PagingConfig(DEFAULT_PAGE_SIZE),
            remoteMediator = PlantsMediator(database = appDatabase, apiService = apiService)
        ) {
            appDatabase.plantDao().getAll()
        }.flow
    }
    fun getPlant(plantId: Int?) = appDatabase.plantDao().getPlant(plantId)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 30
    }
}
