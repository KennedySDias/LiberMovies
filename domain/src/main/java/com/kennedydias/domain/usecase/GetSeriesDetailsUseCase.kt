package com.kennedydias.domain.usecase

import com.kennedydias.data.api.repository.MoviesRepository
import com.kennedydias.data.api.responsemodel.MovieFullResponseModel

class GetSeriesDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MovieFullResponseModel>() {

    var imdbId: String? = null
    var title: String? = null
    var year: String? = null

    override suspend fun executeOnBackground(): MovieFullResponseModel {
        return moviesRepository.getMovieDetails(
            imdbId = imdbId,
            title = title,
            type = "series",
            year = year
        )
    }

}