package com.ascstb.mangaapp5.presentation.mangaDetails

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.local.LocalRepository
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class MangaDetailsViewModel(
    private val repository: ServerRepository,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var manga: Manga? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    var availableChapters = emptyList<MangaChapter>()
        set(value) {
            field = value
            recyclerItemsViewModel =
                value.map { MangaChapterViewModel().apply { chapter = it } }.toMutableList()
            notifyPropertyChanged(BR.availableChapters)
            notifyChange()
        }

    val title: String?
        get() = manga?.title

    val coverUrl: String?
        get() = manga?.coverUrl

    val authors: String?
        get() = manga?.authors?.joinToString { "," }

    val artists: String?
        get() = manga?.artists?.joinToString { "," }

    val status: String?
        get() = manga?.status

    var recyclerItemsViewModel = mutableListOf<MangaChapterViewModel>()
        private set

    @get:Bindable
    var bookmarked: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.bookmarked)
        }

    fun getDetailsAsync() = background {
        Timber.d("MangaDetailsViewModel_TAG: getDetailsAsync: ")
        manga?.let {
            repository.getMangaDetailsAsync(it.url).runOnResult {
                when (this) {
                    is RepositoryResponse.Error -> Timber.d("MangaDetailsViewModel_TAG: getDetailsAsync: error: $error")
                    is RepositoryResponse.Ok -> {
                        manga?.authors = result.authors
                        manga?.artists = result.artists
                        manga?.status = result.status
                        manga?.chapterList = result.chapterList
                        availableChapters = result.chapterList

                        getBookmarkedMangaAsync()
                    }
                }
            }
        }
    }

    fun bookmarkManga() = background {
        Timber.d("MangaDetailsViewModel_TAG: bookmarkManga: ")
        manga?.let {
            localRepository.addBookmarkAsync(it).runOnResult {
                when (this) {
                    is RepositoryResponse.Error -> Timber.d("MangaDetailsViewModel_TAG: bookmarkManga: error: $error")
                    is RepositoryResponse.Ok -> {
                        bookmarked = result
                    }
                }
            }
        }
    }

    private fun getBookmarkedMangaAsync() = background {
        Timber.d("MangaDetailsViewModel_TAG: getBookmaredManga: ")
        manga?.let {
            localRepository.getBookmarkAsync(it).runOnResult {
                when (this) {
                    is RepositoryResponse.Error -> Timber.d("MangaDetailsViewModel_TAG: getBookmarkedMangaAsync: error: $error")
                    is RepositoryResponse.Ok -> bookmarked = result != null
                }
            }
        }
    }
}