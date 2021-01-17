package com.pri.plants.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("common_name") val common_name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("scientific_name") val scientific_name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("bibliography") val bibliography: String,
    @SerializedName("author") val author: String,
    @SerializedName("status") val status: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("family_common_name") val family_common_name: String,
    @SerializedName("genus_id") val genus_id: Int,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("synonyms") val synonyms: List<String>,
    @SerializedName("genus") val genus: String,
    @SerializedName("family") val family: String,
    @SerializedName("links") val links: Links
)