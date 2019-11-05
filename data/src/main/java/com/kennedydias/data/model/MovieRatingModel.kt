package com.kennedydias.data.model

import com.google.gson.annotations.SerializedName

data class MovieRatingModel(
    @SerializedName("Source") val source: String? = null,
    @SerializedName("Value") val value: String? = null
)