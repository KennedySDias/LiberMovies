package com.kennedydias.domain.di

import com.kennedydias.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.domain.usecase.GetMoviesListUseCase
import com.kennedydias.domain.usecase.GetSeriesDetailsUseCase
import com.kennedydias.domain.usecase.GetSeriesListUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val UseCases: Module = module {
    factory { GetMoviesListUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
    factory { GetSeriesListUseCase(get()) }
    factory { GetSeriesDetailsUseCase(get()) }
}