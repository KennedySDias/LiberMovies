package com.kennedydias.domain.usecase

import com.kennedydias.data.MoviesRepository
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.model.MovieShortData

class GetFavoritesMoviesUseCase(
    private val moviesRepository: MoviesRepository,
    private val movieMapper: MovieMapper
) : UseCase<List<MovieShortData>?>() {

    override suspend fun executeOnBackground(): List<MovieShortData>? {
        val response = moviesRepository.getFavoritesMovies()

        return response.map { movieMapper.mapShort(it) }
    }

}