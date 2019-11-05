package com.kennedydias.data.di

import com.kennedydias.data.remote.RetrofitBuilder
import com.kennedydias.data.remote.repository.MoviesRepository
import com.kennedydias.data.remote.repository.MoviesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val RepositoryModules: Module = module {
    factory { MoviesRepositoryImpl(get()) as MoviesRepository }
}

val CommonModules: Module = module {
    factory { RetrofitBuilder(androidContext().cacheDir) }
}