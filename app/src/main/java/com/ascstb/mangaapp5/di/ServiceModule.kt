package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.repository.remote.ServerRepositoryImpl
import com.ascstb.mangaapp5.repository.remote.mangaTown.MangaTownAPI
import org.koin.dsl.module

val serviceModule = module {
    single<MangaTownAPI> { createWebService(get(), BuildConfig.API_SERVER_MANGATOWN) }
    single<ServerRepository> { ServerRepositoryImpl(get()) }
}