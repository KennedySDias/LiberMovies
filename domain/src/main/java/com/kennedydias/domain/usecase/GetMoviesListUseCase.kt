package com.kennedydias.domain.usecase

import com.kennedydias.data.model.MoviesListModel
import com.kennedydias.data.remote.repository.MoviesService

class GetMoviesListUseCase(
    private val moviesRepository: MoviesService
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