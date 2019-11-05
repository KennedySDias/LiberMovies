package com.kennedydias.libermovies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kennedydias.domain.usecase.GetMoviesListUseCase
import com.kennedydias.domain.usecase.GetSeriesListUseCase
import com.kennedydias.commom.expection.NotConnectedException
import com.kennedydias.commom.expection.UnauthorizedException
import java.util.concurrent.TimeoutException

class MoviesViewModel(
    private val moviesListUseCase: GetMoviesListUseCase,
    private val seriesListUseCase: GetSeriesListUseCase
) : ViewModel() {

    val moviesOb = MutableLiveData<List<MovieData>>()
    val seriesOb = MutableLiveData<List<MovieData>>()
    val errorOb = MutableLiveData<String>()
    val fatalErrorOb = MutableLiveData<String>()
    val notConnectedOb = MutableLiveData<Boolean>()
    val gettingMoviesOb = MutableLiveData<Boolean>()
    val gettingSeriesOb = MutableLiveData<Boolean>()

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
                moviesOb.value = it.search?.map { movie ->
                    MovieData.fromMoviesShortResponseModel(movie)
                } ?: emptyList()
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
                seriesOb.value = it.search?.map { movie ->
                    MovieData.fromMoviesShortResponseModel(movie)
                } ?: emptyList()
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

}