package com.ascstb.mangaapp5.presentation.bookmarks

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.presentation.home.MangaItemViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.local.LocalRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class BookmarksViewModel(
    private val repository: LocalRepository
) : BaseViewModel() {
    @get:Bindable
    var availableMangas = emptyList<Manga>()
        set(value) {
            field = value
            recyclerViewItemViewModel = value.map {
                MangaItemViewModel().apply { manga = it }
            }.toMutableList()
            notifyPropertyChanged(BR.availableMangas)
        }

    var recyclerViewItemViewModel = mutableListOf<MangaItemViewModel>()
        private set

    fun getBookmarksAsync() = background {
        Timber.d("BookmarksViewModel_TAG: getBookmarksAsync: ")
        repository.getBookmarksAsync().runOnResult {
            when (this) {
                is RepositoryResponse.Error -> {
                    loading = false
                    Timber.d("BookmarksViewModel_TAG: getBookmarksAsync: error: $error")
                }
                is RepositoryResponse.Ok -> availableMangas = result
            }
        }
    }
}