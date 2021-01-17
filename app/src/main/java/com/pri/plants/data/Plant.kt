package com.pri.plants.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.pri.plants.db.Converters

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
    @Ignore
    @SerializedName("links") var links: Links? = null
){
    val description
    get() = "<b>Genus:<\b> $genus\nFamily: $family"
}