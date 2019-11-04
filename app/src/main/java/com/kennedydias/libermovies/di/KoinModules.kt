package com.kennedydias.libermovies.di

import com.kennedydias.libermovies.data.api.repository.MoviesRepository
import com.kennedydias.libermovies.data.api.repository.MoviesRepositoryImpl
import com.kennedydias.libermovies.data.api.RetrofitBuilder
import com.kennedydias.libermovies.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.libermovies.domain.usecase.GetMoviesListUseCase
import com.kennedydias.libermovies.domain.usecase.GetSeriesDetailsUseCase
import com.kennedydias.libermovies.domain.usecase.GetSeriesListUseCase
import com.kennedydias.libermovies.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val RepositoryModules: Module = module {
    factory { MoviesRepositoryImpl(get()) as MoviesRepository }
}

val CommonModules: Module = module {
    single { RetrofitBuilder() }
}

val UseCases: Module = module {
    factory { GetMoviesListUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
    factory { GetSeriesListUseCase(get()) }
    factory { GetSeriesDetailsUseCase(get()) }
}

val ViewModelModules: Module = module {
    viewModel { MoviesViewModel(get(), get()) }
}