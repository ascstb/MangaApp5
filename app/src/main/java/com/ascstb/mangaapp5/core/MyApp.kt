package com.ascstb.mangaapp5.core

import android.app.Application
import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

@ExperimentalStdlibApi
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()

            androidContext(this@MyApp)
            modules(
                myAppModule +
                        apiModule +
                        serviceModule +
                        homeModule +
                        searchModule
            )
        }
    }
}