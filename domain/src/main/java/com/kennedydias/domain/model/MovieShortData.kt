package com.kennedydias.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieShortData(
    val title: String,
    val poster: String,
    val imdbID: String,
    val type: String,
    val year: String
) : Parcelable