package com.kennedydias.data.api.repository

import com.kennedydias.data.BuildConfig
import com.kennedydias.data.api.responsemodel.MovieFullResponseModel
import com.kennedydias.data.api.responsemodel.MoviesListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String? = null,
        @Query("type") type: String? = "movie",
        @Query("y") year: String? = null,
        @Query("page") page: Int? = 1,
        @Query("r") typeReturn: String? = "json",
        @Query("apikey") apiKey: String? = BuildConfig.OMBD_API_KEY,
        @Query("v") apiVersion: Int? = 1
    ): MoviesListResponseModel

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String? = null,
        @Query("t") title: String? = null,
        @Query("type") type: String? = "movie",
        @Query("y") year: String? = null,
        @Query("plot") plot: String? = "full",
        @Query("r") typeReturn: String? = "json",
        @Query("apikey") apiKey: String? = BuildConfig.OMBD_API_KEY,
        @Query("v") apiVersion: Int? = 1
    ): MovieFullResponseModel

}