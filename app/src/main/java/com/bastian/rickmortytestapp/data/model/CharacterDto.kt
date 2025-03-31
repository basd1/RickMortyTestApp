package com.bastian.rickmortytestapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: LocationSummaryDto,
    @SerializedName("location") val location: LocationSummaryDto,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episodes: List<String>
)

data class LocationSummaryDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)