package com.kennedydias.libermovies.di

import androidx.room.Room
import com.kennedydias.data.MoviesRepository
import com.kennedydias.data.cache.MoviesDatabase
import com.kennedydias.data.remote.RetrofitBuilder
import com.kennedydias.data.remote.repository.MoviesService
import com.kennedydias.data.remote.repository.MoviesServiceImpl
import com.kennedydias.domain.mapper.MovieMapper
import com.kennedydias.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.domain.usecase.GetMoviesListUseCase
import com.kennedydias.domain.usecase.GetSeriesDetailsUseCase
import com.kennedydias.domain.usecase.GetSeriesListUseCase
import com.kennedydias.libermovies.ui.details.DetailsViewModel
import com.kennedydias.libermovies.ui.movies.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ViewModelModules: Module = module {
    viewModel { MoviesViewModel(get(), get()) }
    viewModel { DetailsViewModel() }
}

val DataModules: Module = module {
    factory { MoviesServiceImpl(get()) as MoviesService }
    factory { MoviesRepository(get(), get()) }

    factory { RetrofitBuilder(androidContext().cacheDir) }
    factory {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "movies-database"
        ).build()
    }
}

val DomainModules: Module = module {
    single { MovieMapper() }

    factory { GetMoviesListUseCase(get(), get()) }
    factory { GetMovieDetailsUseCase(get(), get()) }
    factory { GetSeriesListUseCase(get(), get()) }
    factory { GetSeriesDetailsUseCase(get(), get()) }
}