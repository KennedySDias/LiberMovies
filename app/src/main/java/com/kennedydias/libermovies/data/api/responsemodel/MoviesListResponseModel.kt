package com.kennedydias.libermovies.data.api.responsemodel

import com.google.gson.annotations.SerializedName

data class MoviesListResponseModel(
    @SerializedName("Search") val search: List<MovieShortResponseModel>? = null,
    @SerializedName("totalResults") val totalResults: String? = null,
    @SerializedName("Response") val response: String? = null,
    @SerializedName("Error") val error: String? = null
)