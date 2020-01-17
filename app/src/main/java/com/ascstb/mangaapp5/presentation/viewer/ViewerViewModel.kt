package com.ascstb.mangaapp5.presentation.viewer

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.presentation.base.BaseViewModel

class ViewerViewModel(

) : BaseViewModel() {
    var manga: Manga? = null
    var chapter: MangaChapter? = null
        set(value) {
            field = value
            notifyChange()
        }

    var currentPageIndex: Int = 0
}