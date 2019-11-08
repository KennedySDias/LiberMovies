package com.kennedydias.domain.usecase

import com.kennedydias.data.MoviesRepository
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.model.MovieShortData

class GetMoviesListUseCase(
    private val moviesRepository: MoviesRepository,
    private val movieMapper: MovieMapper
) : UseCase<List<MovieShortData>>() {

    var search: String? = null
    var year: String? = null
    var page: Int? = null

    override suspend fun executeOnBackground(): List<MovieShortData> {
        val response = moviesRepository.getMoviesList(
            search = search,
            type = "movie",
            year = year,
            page = page
        )

        if (response.error?.isNotEmpty() == true) throw Exception(response.error)

        return response.search?.map { movieMapper.mapShort(it) } ?: listOf()
    }

}