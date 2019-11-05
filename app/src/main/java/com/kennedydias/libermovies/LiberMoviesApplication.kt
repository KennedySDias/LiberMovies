package com.kennedydias.libermovies

import android.app.Application
import com.kennedydias.data.di.CommonModules
import com.kennedydias.data.di.RepositoryModules
import com.kennedydias.domain.di.UseCases
import com.kennedydias.libermovies.di.ViewModelModules
import org.koin.core.context.startKoin

class LiberMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        configureKoin()
    }

    private fun configureKoin() {
        val appModules = listOf(
            RepositoryModules,
            CommonModules,
            UseCases,
            ViewModelModules
        )

        startKoin {
            modules(appModules)
        }
    }

    companion object {

        lateinit var instance: LiberMoviesApplication

    }

}