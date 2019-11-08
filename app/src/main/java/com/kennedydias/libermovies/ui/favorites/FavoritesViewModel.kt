package com.kennedydias.libermovies.ui.favorites

import androidx.lifecycle.ViewModel
import com.kennedydias.commom.SingleLiveEvent
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.domain.usecase.GetFavoritesMoviesUseCase

class FavoritesViewModel(
    private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase
) : ViewModel() {

    val moviesOb = SingleLiveEvent<List<MovieShortData>>()
    val errorOb = SingleLiveEvent<String>()
    val seeMoreOb = SingleLiveEvent<MovieShortData>()

    fun getFavorites() {
        getFavoritesMoviesUseCase.execute {

            onComplete {
                moviesOb.value = it
            }

            onError { error ->
                errorOb.value = error.message
            }

        }
    }

    fun seeMore(movie: MovieShortData) {
        seeMoreOb.value = movie
    }

}