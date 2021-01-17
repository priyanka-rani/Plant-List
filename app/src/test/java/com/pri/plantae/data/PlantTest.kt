package com.pri.plantae.data

import com.pri.plantae.BuildConfig
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class PlantTest {

    private lateinit var plant: Plant

    @Before
    fun setUp() {
        plant = Plant(id = 189539, common_name = "White clover", slug ="trifolium-repens",
        scientific_name = "Trifolium repens", year = 1753,bibliography = "Sp. Pl.: 767 (1753)", author = "L.",
        status = "accepted",rank = "species",family_common_name = "Pea family", genus_id = 5044,
        image_url = "https://bs.floristic.org/image/o/c766ed84c547abac6021244bc0014d665ba7726f",
        synonyms =  listOf(
            "Amoria repens",
            "Trifolium repens var. atropurpureum",
            "Trifolium repens subsp. repens",
            "Trifolium repens var. giganteum"
        ),
        genus = "Trifolium", family = "Fabaceae",
        links =PlantLinks(
            self = "/api/v1/species/trifolium-repens",
            plant = "/api/v1/plants/trifolium-repens",
            genus = "/api/v1/genus/trifolium"
        )
        )
    }
    @Test fun test_default_values() {
        val defaultPlant = Plant(common_name = "Apple")
        assertEquals(0, defaultPlant.id)
        assertEquals(null, defaultPlant.image_url)
    }
    @Test
    fun test_description() {
        assertEquals("Also known as Amoria repens, Trifolium repens var. atropurpureum, Trifolium repens subsp. repens, Trifolium repens var. giganteum <br><br>" +
                "<b>Slug:</b> trifolium-repens <br>" +
                "<b>Genus:</b> Trifolium <br>" +
                "<b>Family:</b> Fabaceae <br>" +
                "<b>Family Common Name:</b> Pea family <br>" +
                "<b>Invented in:</b> 1753 <br>"+
                "<b>Author:</b> L. <br>"+
                "<b>Bibliography:</b> Sp. Pl.: 767 (1753) <br>"+
                "<a href=${BuildConfig.BASE_URL}/api/v1/species/trifolium-repens?token=${BuildConfig.API_TOKEN}>See More</a>",
            plant.description)
    }

}