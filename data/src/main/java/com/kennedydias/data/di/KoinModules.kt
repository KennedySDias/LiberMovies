package com.kennedydias.data.di

import com.kennedydias.data.api.RetrofitBuilder
import com.kennedydias.data.api.repository.MoviesRepository
import com.kennedydias.data.api.repository.MoviesRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val RepositoryModules: Module = module {
    factory { MoviesRepositoryImpl(get()) as MoviesRepository }
}

val CommonModules: Module = module {
    single { RetrofitBuilder() }
}