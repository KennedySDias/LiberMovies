package com.kennedydias.domain.usecase

import com.kennedydias.data.model.MoviesListModel
import com.kennedydias.data.remote.repository.MoviesRepository

class GetMoviesListUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MoviesListModel>() {

    var search: String? = null
    var year: String? = null
    var page: Int? = null

    override suspend fun executeOnBackground(): MoviesListModel {
        return moviesRepository.getMoviesList(
            search = search,
            type = "movie",
            year = year,
            page = page
        )
    }

}