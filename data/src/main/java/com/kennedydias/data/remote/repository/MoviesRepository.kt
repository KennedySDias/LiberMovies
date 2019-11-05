package com.kennedydias.data.remote.repository

import com.kennedydias.data.model.MovieFullModel
import com.kennedydias.data.model.MoviesListModel
import com.kennedydias.data.remote.RetrofitBuilder

interface MoviesRepository {

    suspend fun getMoviesList(
        search: String? = null,
        type: String? = null,
        year: String? = null,
        page: Int? = null
    ): MoviesListModel

    suspend fun getMovieDetails(
        imdbId: String? = null,
        title: String? = null,
        type: String? = null,
        year: String? = null,
        plot: String? = null
    ): MovieFullModel

}

class MoviesRepositoryImpl(private val retrofit: RetrofitBuilder) :
    MoviesRepository {

    override suspend fun getMoviesList(
        search: String?,
        type: String?,
        year: String?,
        page: Int?
    ): MoviesListModel {
        return retrofit
            .build(MoviesAPI::class.java)
            .getMovies(search, type, year, page)
    }

    override suspend fun getMovieDetails(
        imdbId: String?,
        title: String?,
        type: String?,
        year: String?,
        plot: String?
    ): MovieFullModel {
        return retrofit
            .build(MoviesAPI::class.java)
            .getMovieDetails(imdbId, title, type, year, plot)
    }

}