package com.ascstb.mangaapp5.presentation.viewer

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.model.MangaPage
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class ViewerViewModel(
    private val repository: ServerRepository
) : BaseViewModel() {
    var manga: Manga? = null

    val availableChapters: List<MangaChapter>
        get() = manga?.chapterList ?: emptyList()

    @get:Bindable
    var chapter: MangaChapter? = null
        set(value) {
            field = value
            pageNumber = 1
            notifyPropertyChanged(BR.chapter)
            notifyChange()
        }

    @get:Bindable
    var availablePages: List<MangaPage> = emptyList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.availablePages)
            notifyChange()
        }

    var pageNumber: Int = 1
        set(value) {
            field = when {
                value <= 1 -> 1
                value > availablePages.size -> availablePages.size
                else -> value
            }

            getPageAsync()
        }

    private val currentPage: MangaPage?
        get() = chapter?.pages?.let {
            if (it.size > pageNumber - 1)
                it[pageNumber - 1]
            else null
        }

    val currentPageImageUrl: String?
        get() = currentPage?.imageUrl

    @get:Bindable
    var imageReady = false
    set(value) {
        field = value
        if (value) {
            notifyPropertyChanged(BR.imageReady)
        }
    }

    private fun getPageAsync() = background {
        Timber.d("ViewerViewModel_TAG: getPageAsync: ")
        chapter?.let { ch ->
            repository.getMangaPageAsync(ch.url, pageNumber).runOnResult {
                when (this) {
                    is RepositoryResponse.Error -> Timber.d("ViewerViewModel_TAG: getPageAsync: error: $error")
                    is RepositoryResponse.Ok -> {
                        chapter?.pages = result.pages
                        //chapter = result
                        availablePages = result.pages
                        notifyChange()
                    }
                }
            }
        }
    }

    val onChapterChanged = fun (mangaChapterClicked: MangaChapter) {
        Timber.d("ViewerViewModel_TAG: chapterChanged: ${mangaChapterClicked.name}")
        chapter = mangaChapterClicked
    }

    fun onImageReady() {
        Timber.d("ViewerViewModel_TAG: onImageReady: ")
        imageReady = true
    }
}