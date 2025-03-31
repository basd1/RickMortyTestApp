package com.bastian.rickmortytestapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val chars: List<CharacterDto>
)
