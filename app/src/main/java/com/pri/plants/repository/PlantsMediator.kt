package com.pri.plants.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.pri.plants.api.ApiService
import com.pri.plants.data.Plant
import com.pri.plants.data.RemoteKey
import com.pri.plants.db.AppDatabase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlantsMediator @Inject constructor(
        private val database: AppDatabase,
        private val apiService: ApiService
) : RemoteMediator<Int, Plant>() {
    private val plantDao = database.plantDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, Plant>
    ): MediatorResult {
        return try {
            // The network load method takes an optional String
            // parameter. For every page after the first, pass the String
            // token returned from the previous page to let it continue
            // from where it left off. For REFRESH, pass null to load the
            // first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null

                LoadType.PREPEND -> return MediatorResult.Success(
                        endOfPaginationReached = true
                )
                // Query remoteKeyDao for the next RemoteKey.
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }

                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(
                                endOfPaginationReached = true
                        )
                    }

                    remoteKey.nextPage
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val response = apiService.getPlants(page = loadKey)

            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            database.withTransaction {
                /*if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery()
                    plantDao.clearAll()
                }*/

                // Update RemoteKey for this query.
                response.links.next?.substringAfterLast("=")?.toIntOrNull()?.let {
                    remoteKeyDao.insertOrReplace(
                            RemoteKey(nextPage = it)
                    )
                }
                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                plantDao.insertAll(response.data)
            }

            MediatorResult.Success(
                    endOfPaginationReached = response.links.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}