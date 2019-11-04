package com.kennedydias.libermovies.domain.usecase

import com.kennedydias.libermovies.data.api.repository.MoviesRepository
import com.kennedydias.libermovies.data.api.responsemodel.MoviesListResponseModel

class GetMoviesListUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MoviesListResponseModel>() {

    var search: String? = null
    var year: String? = null
    var page: Int? = null

    override suspend fun executeOnBackground(): MoviesListResponseModel {
        return moviesRepository.getMoviesList(
            search = search,
            type = "movie",
            year = year,
            page = page
        )
    }

}