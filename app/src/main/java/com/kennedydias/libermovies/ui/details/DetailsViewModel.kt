package com.kennedydias.libermovies.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.kennedydias.commom.SingleLiveEvent
import com.kennedydias.commom.expection.NotConnectedException
import com.kennedydias.commom.expection.UnauthorizedException
import com.kennedydias.domain.model.MovieFullData
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.domain.usecase.GetFavoriteMovieUseCase
import com.kennedydias.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.domain.usecase.RemoveFavoriteMovieUseCase
import com.kennedydias.domain.usecase.SaveFavoriteMovieUseCase
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.utils.ResourcesUtils
import java.util.concurrent.TimeoutException

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val resourcesUtils: ResourcesUtils
) : ViewModel() {

    val movieOb = SingleLiveEvent<MovieFullData>()
    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()
    val isFavorite = SingleLiveEvent<Boolean>()

    fun init(arguments: Bundle?) {
        val movieShortData =
            arguments?.getParcelable<MovieShortData>(DetailsFragment.PARAMETER_MOVIE)

        if (movieShortData != null) {
            movieOb.value = MovieFullData(
                title = movieShortData.title,
                imdbID = movieShortData.imdbID,
                type = movieShortData.type,
                year = movieShortData.year,
                poster = movieShortData.poster
            )

            getDetails()
            getFavorite()
        } else {
            fatalErrorOb.value = resourcesUtils.getString(R.string.movie_is_missing)
        }
    }

    fun getDetails() {
        notConnectedOb.value = false
        gettingDataOb.value = true

        getMovieDetailsUseCase.imdbId = movieOb.value?.imdbID ?: ""
        getMovieDetailsUseCase.execute {

            onComplete {
                gettingDataOb.value = false
                movieOb.value = it
            }

            onError { error ->
                gettingDataOb.value = false
                when (error) {
                    is UnauthorizedException -> {
                        fatalErrorOb.value = error.message
                    }
                    is TimeoutException -> {
                        errorOb.value = error.message
                    }
                    is NotConnectedException -> {
                        notConnectedOb.value = true
                    }
                    else -> {
                        errorOb.value = error.message
                    }
                }
            }

            onCancel {
                gettingDataOb.value = false
            }

        }
    }

    fun addOrRemoveFavorite() {
        if (isFavorite.value == true) {
            removeFromFavorites()
        } else {
            saveAsFavorite()
        }
    }

    private fun getFavorite() {
        getFavoriteMovieUseCase.imdbId = movieOb.value?.imdbID
        getFavoriteMovieUseCase.execute {

            onComplete {
                isFavorite.value = it != null
            }

            onError { error ->
                errorOb.value = error.message
            }

        }
    }

    private fun saveAsFavorite() {
        movieOb.value?.let { movie ->
            saveFavoriteMovieUseCase.movie = movie
            saveFavoriteMovieUseCase.execute {

                onComplete {
                    getFavorite()
                }

                onError { error ->
                    errorOb.value = error.message
                }

            }
        }
    }

    private fun removeFromFavorites() {
        movieOb.value?.let { movie ->
            removeFavoriteMovieUseCase.movie = movie
            removeFavoriteMovieUseCase.execute {

                onComplete {
                    getFavorite()
                }

                onError { error ->
                    errorOb.value = error.message
                }

            }
        }
    }

}