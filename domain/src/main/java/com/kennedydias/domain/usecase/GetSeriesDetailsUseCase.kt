package com.kennedydias.domain.usecase

import com.kennedydias.data.model.MovieFullModel
import com.kennedydias.data.remote.repository.MoviesRepository

class GetSeriesDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MovieFullModel>() {

    var imdbId: String? = null
    var title: String? = null
    var year: String? = null

    override suspend fun executeOnBackground(): MovieFullModel {
        return moviesRepository.getMovieDetails(
            imdbId = imdbId,
            title = title,
            type = "series",
            year = year
        )
    }

}