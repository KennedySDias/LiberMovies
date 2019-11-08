package com.kennedydias.domain.usecase

import com.kennedydias.data.MoviesRepository
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.model.MovieFullData

class RemoveFavoriteMovieUseCase(
    private val moviesRepository: MoviesRepository,
    private val movieMapper: MovieMapper
) : UseCase<Boolean>() {

    lateinit var movie: MovieFullData

    override suspend fun executeOnBackground(): Boolean {
        return try {
            moviesRepository.removeFavoriteMovie(movieMapper.mapToShortModel(movie))
            true
        } catch (e: Exception) {
            false
        }
    }

}