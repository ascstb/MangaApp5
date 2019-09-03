package com.ascstb.mangaapp5.core

import com.ascstb.mangaapp5.model.MangaProvidersEnum

object Session {
    var userId: String = ""
    var columnsToDisplay: Int = 3
    var selectedProvider: MangaProvidersEnum = MangaProvidersEnum.MANGATOWN
}