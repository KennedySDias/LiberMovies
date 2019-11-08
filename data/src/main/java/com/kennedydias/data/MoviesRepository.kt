package com.kennedydias.data

import com.kennedydias.data.cache.MoviesDatabase
import com.kennedydias.data.model.MovieFullModel
import com.kennedydias.data.model.MovieShortModel
import com.kennedydias.data.model.MoviesListModel
import com.kennedydias.data.remote.repository.MoviesService

class MoviesRepository(
    private val remoteService: MoviesService,
    private val localService: MoviesDatabase
) {

    suspend fun getMoviesList(
        search: String? = null,
        type: String? = null,
        year: String? = null,
        page: Int? = null
    ): MoviesListModel {
        return remoteService.getMoviesList(search, type, year, page)
    }

    suspend fun getMovieDetails(
        imdbId: String? = null,
        title: String? = null,
        type: String? = null,
        year: String? = null
    ): MovieFullModel {
        return remoteService.getMovieDetails(imdbId, title, type, year)
    }

    suspend fun getFavoritesMovies(): List<MovieShortModel> {
        return localService.favoritesMoviesDao().getFavoritesMovies()
    }

    suspend fun getFavoriteMovie(imdb: String): MovieShortModel? {
        return try {
            localService.favoritesMoviesDao().getFavoriteMovie(imdb).first()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    suspend fun saveFavoriteMovie(movie: MovieShortModel) {
        localService.favoritesMoviesDao().insertFavoriteMovie(movie)
    }

    suspend fun removeFavoriteMovie(movie: MovieShortModel) {
        localService.favoritesMoviesDao().deleteFavoriteMovie(movie)
    }

    suspend fun saveFavoritesMovie(movie: MovieShortModel): Boolean {
        return try {
            localService.favoritesMoviesDao().insertFavoriteMovie(movie)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun removeFavoritesMovie(movie: MovieShortModel): Boolean {
        return try {
            localService.favoritesMoviesDao().deleteFavoriteMovie(movie)
            true
        } catch (e: Exception) {
            false
        }
    }

}