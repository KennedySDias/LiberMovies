package com.kennedydias.libermovies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kennedydias.commom.expection.NotConnectedException
import com.kennedydias.commom.expection.UnauthorizedException
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.domain.usecase.GetMoviesListUseCase
import com.kennedydias.domain.usecase.GetSeriesListUseCase
import java.util.concurrent.TimeoutException

class MoviesViewModel(
    private val moviesListUseCase: GetMoviesListUseCase,
    private val seriesListUseCase: GetSeriesListUseCase
) : ViewModel() {

    val moviesOb = MutableLiveData<List<MovieShortData>>()
    val seriesOb = MutableLiveData<List<MovieShortData>>()
    val errorOb = MutableLiveData<String>()
    val fatalErrorOb = MutableLiveData<String>()
    val notConnectedOb = MutableLiveData<Boolean>()
    val gettingMoviesOb = MutableLiveData<Boolean>()
    val gettingSeriesOb = MutableLiveData<Boolean>()
    val seeMoreOb = MutableLiveData<MovieShortData>()

    var searchOb: String? = null

    fun init() {
        getMovies()
        getSeries()
    }

    fun getMovies() {
        notConnectedOb.value = false
        gettingMoviesOb.value = true

        moviesListUseCase.search = searchOb
        moviesListUseCase.execute {

            onComplete {
                gettingMoviesOb.value = false
                moviesOb.value = it
            }

            onError { error ->
                gettingMoviesOb.value = false
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

            onCancel { throwable ->
                gettingMoviesOb.value = false
            }

        }
    }

    fun getSeries() {
        notConnectedOb.value = false
        gettingSeriesOb.value = true

        seriesListUseCase.search = searchOb
        seriesListUseCase.execute {

            onComplete {
                gettingSeriesOb.value = false
                seriesOb.value = it
            }

            onError { error ->
                gettingSeriesOb.value = false
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

            onCancel { throwable ->
                gettingSeriesOb.value = false
            }

        }
    }

    fun seeMore(movie: MovieShortData) {
        seeMoreOb.value = movie
    }

}