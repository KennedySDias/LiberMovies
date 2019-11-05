package com.kennedydias.libermovies.di

import androidx.room.Room
import com.kennedydias.data.MoviesRepository
import com.kennedydias.data.cache.MoviesDatabase
import com.kennedydias.data.remote.RetrofitBuilder
import com.kennedydias.data.remote.repository.MoviesService
import com.kennedydias.data.remote.repository.MoviesServiceImpl
import com.kennedydias.domain.usecase.GetMovieDetailsUseCase
import com.kennedydias.domain.usecase.GetMoviesListUseCase
import com.kennedydias.domain.usecase.GetSeriesDetailsUseCase
import com.kennedydias.domain.usecase.GetSeriesListUseCase
import com.kennedydias.libermovies.ui.movies.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ViewModelModules: Module = module {
    viewModel { MoviesViewModel(get(), get()) }
}

val RepositoryModules: Module = module {
    factory { MoviesServiceImpl(get()) as MoviesService }
    factory { MoviesRepository(get(), get()) }
}

val CommonModules: Module = module {
    factory { RetrofitBuilder(androidContext().cacheDir) }
    factory {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "movies-database"
        ).build()
    }
}

val UseCases: Module = module {
    factory { GetMoviesListUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
    factory { GetSeriesListUseCase(get()) }
    factory { GetSeriesDetailsUseCase(get()) }
}