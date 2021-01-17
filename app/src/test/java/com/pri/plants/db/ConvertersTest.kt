package com.pri.plants.db

import com.google.gson.Gson
import com.pri.plants.data.PlantLinks
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ConvertersTest{
    private val plantSynonyms = listOf("Anthoxanthum odoratum f. montanum",
        "Anthoxanthum odoratum f. tenerum",
        "Anthoxanthum sommierianum",
        "Anthoxanthum maderense",
        "Xanthonanthos odoratus",
        "Anthoxanthum pauciflorum",
        "Anthoxanthum asperum",
        "Anthoxanthum nebrodense")
    private val plantLinks = PlantLinks(
        self = "/api/v1/species/quercus-rotundifolia",
        plant = "/api/v1/plants/quercus-rotundifolia",
        genus = "/api/v1/genus/quercus"
    )
    private val converters = Converters()

    @Test
    fun stringListToString() {
        assertEquals(plantSynonyms.joinToString(), converters.stringListToString(plantSynonyms))
    }

    @Test
    fun stringToStringList() {
        assertEquals(Converters().stringToStringList(plantSynonyms.joinToString()), plantSynonyms)
    }

    @Test
    fun stringToPlantLink() {
        assertEquals(plantLinks, converters.stringToPlantLink(Gson().toJson(plantLinks)))
    }

    @Test
    fun plantLinksToString() {
        assertEquals(Gson().toJson(plantLinks), converters.plantLinksToString(plantLinks))
    }
}