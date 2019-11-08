package com.kennedydias.libermovies.ui.movies

import androidx.lifecycle.ViewModel
import com.kennedydias.commom.SingleLiveEvent
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

    private var moviesOriginalFilterOb = listOf<MovieShortData>()
    private var seriesOriginalFilterOb = listOf<MovieShortData>()
    private val initialSearchList =
        listOf("Marvel", "Comics", "Disney", "Pokémon", "Super", "Anime")

    val moviesOb = SingleLiveEvent<List<MovieShortData>>()
    val seriesOb = SingleLiveEvent<List<MovieShortData>>()
    val errorOb = SingleLiveEvent<String>()
    val fatalErrorOb = SingleLiveEvent<String>()
    val notConnectedOb = SingleLiveEvent<Boolean>()
    val gettingDataOb = SingleLiveEvent<Boolean>()
    val seeMoreOb = SingleLiveEvent<MovieShortData>()
    val openMoviesFilterOb = SingleLiveEvent<Boolean>()
    val openSeriesFilterOb = SingleLiveEvent<Boolean>()

    var searchOb: String? = null

    fun init() {
        getMovies()
        getSeries()
    }

    fun getMovies() {
        notConnectedOb.value = false
        gettingDataOb.value = true

        moviesListUseCase.search = searchOb
        moviesListUseCase.execute {

            onComplete {
                gettingDataOb.value = false
                moviesOb.value = it
                moviesOriginalFilterOb = moviesOb.value ?: emptyList()
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

            onCancel { throwable ->
                gettingDataOb.value = false
            }

        }
    }

    fun getSeries() {
        notConnectedOb.value = false
        gettingDataOb.value = true

        seriesListUseCase.search = searchOb
        seriesListUseCase.execute {

            onComplete {
                gettingDataOb.value = false
                seriesOb.value = it
                seriesOriginalFilterOb = seriesOb.value ?: emptyList()
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

            onCancel { throwable ->
                gettingDataOb.value = false
            }

        }
    }

    fun seeMore(movie: MovieShortData) {
        seeMoreOb.value = movie
    }

    fun openMoviesFilter() {
        openMoviesFilterOb.value = true
    }

    fun getInitialSearch(): String {
        return initialSearchList.random()
    }

    fun filterMoviesByName() {
        moviesOb.value = moviesOb.value?.sortedBy { it.title }
    }

    fun filterMoviesByYear() {
        moviesOb.value = moviesOb.value?.sortedBy {
            try {
                it.year.toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
    }

    fun filterMoviesByRelevance() {
        if (moviesOriginalFilterOb.isNotEmpty()) {
            moviesOb.value = moviesOriginalFilterOb
        }
    }

    fun openSeriesFilter() {
        openSeriesFilterOb.value = true
    }

    fun filterSeriesByName() {
        seriesOb.value = seriesOb.value?.sortedBy { it.title }
    }

    fun filterSeriesByYear() {
        seriesOb.value = seriesOb.value?.sortedBy {
            try {
                /**
                 *  Get the start year from series
                 *  EX: some cases came as "1999-" or "2000" or "2000-2006"
                 *
                 *  So I used an regex expression to get it
                 */
                getStartYearFromSeries(it.year)
            } catch (e: NumberFormatException) {
                0
            }
        }
    }

    fun filterSeriesByRelevance() {
        if (seriesOriginalFilterOb.isNotEmpty()) {
            seriesOb.value = seriesOriginalFilterOb
        }
    }

    private fun getStartYearFromSeries(year: String): Int {
        return Regex("^[^;–]*").find(year)?.value.toString().toInt()
    }

}