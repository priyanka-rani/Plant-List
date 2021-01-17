package com.pri.plantae.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.pri.plantae.BuildConfig

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,
    @SerializedName("common_name") var common_name: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("scientific_name") var scientific_name: String? = null,
    @SerializedName("year") var year: Int? = null,
    @SerializedName("bibliography") var bibliography: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("rank") var rank: String? = null,
    @SerializedName("family_common_name") var family_common_name: String? = null,
    @SerializedName("genus_id") var genus_id: Int? = null,
    @SerializedName("image_url") var image_url: String? = null,
    @SerializedName("synonyms") var synonyms: List<String>? = null,
    @SerializedName("genus") var genus: String? = null,
    @SerializedName("family") var family: String? = null,
    @SerializedName("links") var links: PlantLinks? = null
){
    val description
    get() = "Also known as ${synonyms?.joinToString()} <br><br>" +
            "<b>Slug:</b> $slug <br>" +
            "<b>Genus:</b> $genus <br>" +
            "<b>Family:</b> $family <br>" +
            "<b>Family Common Name:</b> $family_common_name <br>" +
            "<b>Invented in:</b> $year <br>"+
            "<b>Author:</b> $author <br>"+
            "<b>Bibliography:</b> $bibliography <br>"+
            "<a href=${BuildConfig.BASE_URL}${links?.self}?token=${BuildConfig.API_TOKEN}>See More</a>"
}

data class PlantLinks(
    @SerializedName("self") val self: String?,
    @SerializedName("plant") val plant: String?,
    @SerializedName("genus") val genus: String?
)