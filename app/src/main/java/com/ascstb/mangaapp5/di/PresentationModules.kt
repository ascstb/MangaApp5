package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.presentation.bookmarks.BookmarksViewModel
import com.ascstb.mangaapp5.presentation.home.FilterViewModel
import com.ascstb.mangaapp5.presentation.home.HomeViewModel
import com.ascstb.mangaapp5.presentation.mangaDetails.MangaDetailsViewModel
import com.ascstb.mangaapp5.presentation.settings.SettingsViewModel
import com.ascstb.mangaapp5.presentation.viewer.ViewerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MangaDetailsViewModel(get(), get()) }
    viewModel { ViewerViewModel(get()) }
    viewModel { BookmarksViewModel(get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}