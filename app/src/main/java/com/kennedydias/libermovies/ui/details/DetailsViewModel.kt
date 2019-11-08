package com.kennedydias.libermovies.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kennedydias.commom.expection.NotConnectedException
import com.kennedydias.commom.expection.UnauthorizedException
import com.kennedydias.domain.model.MovieFullData
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.utils.ResourcesUtils
import java.util.concurrent.TimeoutException

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val resourcesUtils: ResourcesUtils
) : ViewModel() {

    val movieOb = MutableLiveData<MovieFullData>()
    val errorOb = MutableLiveData<String>()
    val fatalErrorOb = MutableLiveData<String>()
    val notConnectedOb = MutableLiveData<Boolean>()
    val gettingDataOb = MutableLiveData<Boolean>()

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

    fun getFavoriteIcon(): Drawable? {
        return resourcesUtils.getDrawable(R.drawable.ic_favorite)
    }

}