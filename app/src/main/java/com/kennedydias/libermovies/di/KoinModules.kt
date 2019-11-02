package com.kennedydias.libermovies.di

import com.kennedydias.libermovies.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val RepositoryModules: Module = module {
}

val CommonModules: Module = module {
}

val ViewModelModules: Module = module {
    viewModel { MoviesViewModel() }
}