package com.ascstb.mangaapp5.presentation.home

import androidx.databinding.Bindable
import com.ascstb.mangaapp5.BR
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class HomeViewModel(
    private val repository: ServerRepository
) : BaseViewModel() {

    @get:Bindable
    var latestManga = emptyList<Manga>()
        set(value) {
            field = value

            val tempList = value.map { MangaItemViewModel().apply { manga = it } }.toMutableList()
            if (currentPage == 1) {
                recyclerItemsViewModel = tempList
            } else {
                recyclerItemsViewModel.addAll(tempList)
            }

            notifyPropertyChanged(BR.latestManga)
        }

    var currentPage: Int = 1
        set(value) {
            field = value
            getLatestMangaAsync()
        }

    var recyclerItemsViewModel = mutableListOf<MangaItemViewModel>()
        private set

    fun getLatestMangaAsync() = background {
        Timber.d("HomeViewModel_TAG: getLatestMangaAsync: ")
        repository.getLatestReleasesAsync(currentPage).runOnResult {
            when (this) {
                is RepositoryResponse.Error -> Timber.d("HomeViewModel_TAG: getLatestMangaAsync: error: $error")
                is RepositoryResponse.Ok -> latestManga = result
            }
        }
    }

    fun endOfScroll() {
        Timber.d("HomeViewModel_TAG: endOfScroll: currentPage: $currentPage")
        currentPage++
    }

    fun refresh() {
        Timber.d("HomeViewModel_TAG: refresh: currentPage: $currentPage")
        currentPage = 1
    }
}