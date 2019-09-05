package com.ascstb.mangaapp5.presentation.home

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    @get:Bindable
    var latestManga = emptyList<Manga>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.latestManga)
        }

    fun getLatestMangaAsync() = background {
        Timber.d("HomeViewModel_TAG: getLatestMangaAsync: ")
        repository.getLatestReleasesAsync().runOnResult {
            when (this) {
                is RepositoryResponse.Error -> Timber.d("HomeViewModel_TAG: getLatestMangaAsync: error: $error")
                is RepositoryResponse.Ok -> latestManga = result
            }
        }
    }
}