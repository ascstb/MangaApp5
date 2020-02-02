package com.ascstb.mangaapp5.presentation.search

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.SearchParameters
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.presentation.home.MangaItemViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class SearchViewModel(
    private val repository: ServerRepository
) : BaseViewModel() {
    @Bindable
    var seriesName: String = ""

    @Bindable
    var authorName: String = ""

    @Bindable
    var artistName: String = ""

    @get:Bindable
    var searchResults = emptyList<Manga>()
        set(value) {
            field = value
            recyclerItemsViewModel =
                value.map { MangaItemViewModel().apply { manga = it } }.toMutableList()
            notifyPropertyChanged(BR.searchResults)
        }

    var recyclerItemsViewModel = mutableListOf<MangaItemViewModel>()
        private set

    fun getResultsAsync() = background {
        Timber.d("SearchViewModel_TAG: getResultsAsync: ")
        repository.getMangaSearchResultsAsync(
            SearchParameters(
                name = seriesName,
                author = authorName,
                artist = artistName
            )
        ).runOnResult {
            loading = false
            when (this) {
                is RepositoryResponse.Error -> Timber.d("SearchViewModel_TAG: getResultsAsync: error: $error")
                is RepositoryResponse.Ok -> {
                    searchResults = result
                }
            }
        }
    }
}