package com.ascstb.mangaapp5.presentation.home

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseViewModel

class MangaItemViewModel : BaseViewModel() {
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