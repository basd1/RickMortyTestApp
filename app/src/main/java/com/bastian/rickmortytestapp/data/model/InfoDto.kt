package com.bastian.rickmortytestapp.data.model

import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("pages") val pages: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)
