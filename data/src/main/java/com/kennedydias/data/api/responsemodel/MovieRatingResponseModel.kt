package com.kennedydias.data.api.responsemodel

import com.google.gson.annotations.SerializedName

data class MovieRatingResponseModel(
    @SerializedName("Source") val source: String? = null,
    @SerializedName("Value") val value: String? = null
)