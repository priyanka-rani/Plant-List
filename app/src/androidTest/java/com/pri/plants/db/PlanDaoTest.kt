package com.pri.plants.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pri.plants.data.Plant
import com.pri.plants.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.hamcrest.Matchers.equalTo

@RunWith(AndroidJUnit4::class)
class PlantDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var plantDao: PlantDao
    private val plantA = Plant(1, "A")
    private val plantB = Plant(2, "B")
    private val plantC = Plant(3, "C")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        plantDao = database.plantDao()

        // Insert plants in non-alphabetical order to test that results are sorted by name
        plantDao.insertAll(
            listOf(
                plantB,
                plantC,
                plantA
            )
        )
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetPlants() {
        val plantList = getValue(plantDao.getPlants())
        assertThat(plantList.size, equalTo(3))

        // Ensure plant list is sorted by name
        assertThat(plantList[0], equalTo(plantA))
        assertThat(plantList[1], equalTo(plantB))
        assertThat(plantList[2], equalTo(plantC))
    }

    @Test
    fun testGetPlant() {
        assertThat(
            getValue(
                plantDao.getPlant(
                    plantA.id
                )
            ), equalTo(plantA)
        )
    }
}