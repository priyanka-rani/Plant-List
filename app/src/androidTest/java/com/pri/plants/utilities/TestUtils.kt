package com.pri.plants.utilities

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import com.pri.plants.data.Plant

/**
 * [Plant] objects used for tests.
 */
val testPlants = arrayListOf(
    Plant(1, "PlanA"),
    Plant(2, "PlantB"),
    Plant(3, "PlantC")
)
val testPlant = testPlants[0]

/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
    activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String

