package com.ascstb.mangaapp5.core

import android.app.Application
import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.di.apiModule
import com.ascstb.mangaapp5.di.homeModule
import com.ascstb.mangaapp5.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()

            androidContext(this@MyApp)
            modules(
                apiModule +
                        serviceModule +
                        homeModule
            )
        }
    }
}