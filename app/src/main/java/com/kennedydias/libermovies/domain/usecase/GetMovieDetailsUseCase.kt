package com.kennedydias.libermovies.domain.usecase

import com.kennedydias.libermovies.data.api.repository.MoviesRepository
import com.kennedydias.libermovies.data.api.responsemodel.MovieFullResponseModel

class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MovieFullResponseModel>() {

    var imdbId: String? = null
    var title: String? = null
    var year: String? = null

    override suspend fun executeOnBackground(): MovieFullResponseModel {
        return moviesRepository.getMovieDetails(
            imdbId = imdbId,
            title = title,
            type = "movie",
            year = year
        )
    }

}