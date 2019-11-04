package com.kennedydias.libermovies.domain.usecase

import com.kennedydias.libermovies.data.api.repository.MoviesRepository
import com.kennedydias.libermovies.data.api.responsemodel.MoviesListResponseModel

class GetSeriesListUseCase(
    private val moviesRepository: MoviesRepository
) : UseCase<MoviesListResponseModel>() {

    var search: String? = null
    var year: String? = null
    var page: Int? = null

    override suspend fun executeOnBackground(): MoviesListResponseModel {
        return moviesRepository.getMoviesList(
            search = search,
            type = "series",
            year = year,
            page = page
        )
    }

}