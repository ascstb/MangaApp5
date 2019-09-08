package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.presentation.navigation.Navigation
import com.ascstb.mangaapp5.presentation.navigation.NavigationImpl
import org.koin.dsl.module

val myAppModule = module {
    single<Navigation> { NavigationImpl() }
}