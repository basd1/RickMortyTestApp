package com.bastian.rickmortytestapp.domain.model

import com.bastian.rickmortytestapp.data.model.CharacterDto
import com.bastian.rickmortytestapp.data.model.LocationDto
import com.bastian.rickmortytestapp.data.model.LocationSummaryDto

fun LocationDto.toDomain() : LocationModel{
    return LocationModel(
        id = id,
        type = type,
        name = name,
        dimension = dimension
    )
}

fun LocationSummaryDto.toDomain() : LocationSummaryModel{
    return LocationSummaryModel(
        name = name,
        url = url
    )
}

fun CharacterDto.toDomain() : CharacterModel{
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDomain(),
        location = location.toDomain(),
        image = image,
        episodes = episodes
    )
}