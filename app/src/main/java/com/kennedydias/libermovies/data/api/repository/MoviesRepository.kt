package com.kennedydias.libermovies.data.api.repository

import com.kennedydias.libermovies.data.api.RetrofitBuilder
import com.kennedydias.libermovies.data.api.responsemodel.MovieFullResponseModel
import com.kennedydias.libermovies.data.api.responsemodel.MoviesListResponseModel

interface MoviesRepository {

    suspend fun getMoviesList(
        search: String? = null,
        type: String? = null,
        year: String? = null,
        page: Int? = 1
    ): MoviesListResponseModel

    suspend fun getMovieDetails(
        imdbId: String? = null,
        title: String? = null,
        type: String? = null,
        year: String? = null,
        plot: String? = null
    ): MovieFullResponseModel

}

class MoviesRepositoryImpl(private val retrofit: RetrofitBuilder) :
    MoviesRepository {

    override suspend fun getMoviesList(
        search: String?,
        type: String?,
        year: String?,
        page: Int?
    ): MoviesListResponseModel {
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
    ): MovieFullResponseModel {
        return retrofit
            .build(MoviesAPI::class.java)
            .getMovieDetails(imdbId, title, type, year, plot)
    }

}