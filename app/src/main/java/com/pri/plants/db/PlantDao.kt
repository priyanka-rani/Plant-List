package com.pri.plants.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pri.plants.data.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAll(userId: Int): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plantList: List<Plant>)
}