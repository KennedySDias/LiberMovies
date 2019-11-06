package com.kennedydias.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kennedydias.data.model.MovieShortModel

@Dao
interface FavoritesMoviesDao {

    @Query(value = "SELECT * FROM favorite_movies")
    suspend fun getFavoritesMovies(): List<MovieShortModel>

    @Insert
    suspend fun insertFavoriteMovie(movie: MovieShortModel)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieShortModel)

}