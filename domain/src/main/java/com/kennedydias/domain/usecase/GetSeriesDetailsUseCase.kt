package com.kennedydias.domain.usecase

import com.kennedydias.data.remote.repository.MoviesService
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.model.MovieFullData

class GetSeriesDetailsUseCase(
    private val moviesRepository: MoviesService,
    private val movieMapper: MovieMapper
) : UseCase<MovieFullData>() {


    var imdbId: String? = null
    var title: String? = null
    var year: String? = null

    override suspend fun executeOnBackground(): MovieFullData {
        val response = moviesRepository.getMovieDetails(
            imdbId = imdbId,
            title = title,
            type = "series",
            year = year
        )

        return movieMapper.mapFull(response)
    }

}