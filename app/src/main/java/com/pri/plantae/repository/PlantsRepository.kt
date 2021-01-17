package com.pri.plantae.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pri.plantae.api.ApiService
import com.pri.plantae.data.Plant
import com.pri.plantae.db.AppDatabase
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
        const val DEFAULT_PAGE_SIZE = 30
    }
}
