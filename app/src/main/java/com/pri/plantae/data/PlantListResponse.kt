package com.pri.plantae.data

import com.google.gson.annotations.SerializedName

data class PlantListResponse(

    @SerializedName("data") val data: List<Plant>,
    @SerializedName("links") val links: Links,
    @SerializedName("meta") val meta: Meta
)

data class Meta(
    @SerializedName("total") val total: Int
)

data class Links(
    @SerializedName("self") val self: String?,
    @SerializedName("first") val first: String?,
    @SerializedName("next") val next: String?,
    @SerializedName("last") val last: String?
)