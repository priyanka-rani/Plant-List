package com.pri.plants.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.pri.plants.db.AppDatabase
import com.pri.plants.repository.PlantsRepository
import com.pri.plants.utilities.getValue
import com.pri.plants.utilities.testPlants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltAndroidTest
class DetailsViewModelTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: DetailsViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var plantRepository: PlantsRepository


    @Before
    fun setUp() = runBlocking {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
        /*insert dummy data*/
        appDatabase.plantDao().insertAll(testPlants)

        viewModel = DetailsViewModel(plantRepository)
        viewModel.plantId.value = 1
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testPlantId() {
        assertEquals(1, getValue(viewModel.plantId))
    }
}