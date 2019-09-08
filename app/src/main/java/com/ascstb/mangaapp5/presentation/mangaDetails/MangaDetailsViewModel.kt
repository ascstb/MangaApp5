package com.ascstb.mangaapp5.presentation.mangaDetails

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.repository.remote.ServerRepository

class MangaDetailsViewModel(
    private val repository: ServerRepository
) : BaseViewModel() {
    var manga: Manga? = null
        set(value) {
            field = value
            notifyChange()
        }

    val title: String?
        get() = manga?.title

    val coverUrl: String?
        get() = manga?.coverUrl
}