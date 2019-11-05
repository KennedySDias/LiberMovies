package com.kennedydias.data.model

import com.google.gson.annotations.SerializedName

data class MoviesListModel(
    @SerializedName("Search") val search: List<MovieShortModel>? = null,
    @SerializedName("totalResults") val totalResults: String? = null,
    @SerializedName("Response") val response: String? = null,
    @SerializedName("Error") val error: String? = null
)