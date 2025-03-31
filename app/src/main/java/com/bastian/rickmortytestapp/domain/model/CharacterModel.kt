package com.bastian.rickmortytestapp.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationSummaryModel,
    val location: LocationSummaryModel,
    val image: String,
    val episodes: List<String>
)

data class LocationSummaryModel(
    val name: String,
    val url: String
)
