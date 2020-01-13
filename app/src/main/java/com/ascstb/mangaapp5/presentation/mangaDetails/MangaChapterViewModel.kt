package com.ascstb.mangaapp5.presentation.mangaDetails

import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.presentation.base.BaseViewModel

class MangaChapterViewModel : BaseViewModel() {
    var chapter: MangaChapter? = null
        set(value) {
            field = value
            notifyChange()
        }

    val name: String?
        get() = chapter?.name

    private val url: String?
        get() = chapter?.url

    val chapterActionHtml: String
        get() = "<a href='$url'>$name</a>"

    val comment: String?
        get() = chapter?.comment

    val updatedAt: String?
        get() = chapter?.updatedAt
}