package com.kennedydias.libermovies.di

import com.kennedydias.libermovies.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.libermovies.domain.usecase.GetMoviesListUseCase
import com.kennedydias.libermovies.domain.usecase.GetSeriesDetailsUseCase
import com.kennedydias.libermovies.domain.usecase.GetSeriesListUseCase
import com.kennedydias.libermovies.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val UseCases: Module = module {
    factory { GetMoviesListUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
    factory { GetSeriesListUseCase(get()) }
    factory { GetSeriesDetailsUseCase(get()) }
}

val ViewModelModules: Module = module {
    viewModel { MoviesViewModel(get(), get()) }
}