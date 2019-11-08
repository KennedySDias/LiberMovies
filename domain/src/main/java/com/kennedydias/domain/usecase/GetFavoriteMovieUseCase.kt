package com.kennedydias.domain.usecase

import com.kennedydias.data.MoviesRepository
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.model.MovieShortData

class GetFavoriteMovieUseCase(
    private val moviesRepository: MoviesRepository,
    private val movieMapper: MovieMapper
) : UseCase<MovieShortData?>() {

    var imdbId: String? = null

    override suspend fun executeOnBackground(): MovieShortData? {
        val response = moviesRepository.getFavoriteMovie(imdbId!!)

        return if (response != null) movieMapper.mapShort(response) else null
    }

}