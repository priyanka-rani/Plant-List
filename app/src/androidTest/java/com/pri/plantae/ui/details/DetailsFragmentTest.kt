package com.pri.plantae.ui.details

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pri.plantae.MainActivity
import com.pri.plantae.R
import com.pri.plantae.utilities.testPlant
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest{
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToPlantDetailFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putInt("plantId", testPlant.id) }
                findNavController(R.id.nav_host).navigate(R.id.detailsFragment, bundle)
            }
        }
    }

    @Ignore("Share button redesign pending")
    @Test
    fun testBackPress() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation
            .performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
    }
}