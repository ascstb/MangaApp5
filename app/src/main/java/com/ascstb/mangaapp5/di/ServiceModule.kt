package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.repository.local.LocalRepository
import com.ascstb.mangaapp5.repository.local.LocalRepositoryImpl
import com.ascstb.mangaapp5.repository.local.database.MangaRepository
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.repository.remote.ServerRepositoryImpl
import com.ascstb.mangaapp5.repository.remote.mangaTown.MangaTownAPI
import com.ascstb.mangaapp5.repository.remote.mangaTown.MangaTownProvider
import org.koin.dsl.module

val serviceModule = module {
    single<MangaTownAPI> { createWebService(get(), BuildConfig.API_SERVER_MANGATOWN) }
    single { MangaTownProvider(get()) }
    single<ServerRepository> { ServerRepositoryImpl(get()) }
    single<LocalRepository> {
        LocalRepositoryImpl(
            mangaRepository = MangaRepository(get())
        )
    }
}