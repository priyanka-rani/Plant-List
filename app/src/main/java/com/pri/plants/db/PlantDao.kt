package com.pri.plants.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pri.plants.data.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<Plant>)

    @Query("SELECT * FROM plants")
    fun getAll(): PagingSource<Int, Plant>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: Int?): LiveData<Plant>

    @Query("SELECT * FROM plants ORDER BY id")
    fun getPlants(): LiveData<List<Plant>>

    @Query("DELETE FROM plants")
    suspend fun clearAll()
}