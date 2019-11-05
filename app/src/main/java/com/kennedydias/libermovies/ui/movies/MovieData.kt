package com.kennedydias.libermovies.ui.movies

import com.kennedydias.data.model.MovieShortModel

class MovieData(
    val title: String,
    val poster: String,
    val imdbID: String,
    val type: String,
    val year: String
) {
    companion object {
        fun fromMoviesShortResponseModel(movie: MovieShortModel) =
            MovieData(
                title = movie.title ?: "",
                poster = movie.poster ?: "",
                imdbID = movie.imdbID ?: "",
                type = movie.type ?: "",
                year = movie.year ?: ""
            )
    }
}