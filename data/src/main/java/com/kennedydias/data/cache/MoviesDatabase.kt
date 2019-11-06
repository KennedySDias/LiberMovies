package com.kennedydias.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kennedydias.data.cache.dao.FavoritesMoviesDao
import com.kennedydias.data.model.MovieShortModel

@Database(entities = [MovieShortModel::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun favoritesMoviesDao(): FavoritesMoviesDao
}