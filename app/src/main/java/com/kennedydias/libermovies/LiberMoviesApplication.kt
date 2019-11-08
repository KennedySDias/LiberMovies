package com.kennedydias.libermovies

import android.app.Application
import com.kennedydias.libermovies.di.CommonModules
import com.kennedydias.libermovies.di.DataModules
import com.kennedydias.libermovies.di.DomainModules
import com.kennedydias.libermovies.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiberMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        configureKoin()
    }

    private fun configureKoin() {
        val appModules = listOf(
            DataModules,
            DomainModules,
            ViewModelModules,
            CommonModules
        )

        startKoin {
            // Android context
            androidContext(this@LiberMoviesApplication)
            // modules
            modules(appModules)
        }
    }

    companion object {
        lateinit var instance: LiberMoviesApplication
    }

}