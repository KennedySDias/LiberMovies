package com.kennedydias.data.api.responsemodel

import com.google.gson.annotations.SerializedName

data class MovieShortResponseModel(
    @SerializedName("Title") val title: String? = null,
    @SerializedName("Year") val year: String? = null,
    @SerializedName("imdbID") val imdbID: String? = null,
    @SerializedName("Type") val type: String? = null,
    @SerializedName("Poster") val poster: String? = null
)