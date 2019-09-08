package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.presentation.home.HomeViewModel
import com.ascstb.mangaapp5.presentation.mangaDetails.MangaDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MangaDetailsViewModel(get()) }
}