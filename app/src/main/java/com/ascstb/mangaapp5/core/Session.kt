package com.ascstb.mangaapp5.core

import androidx.fragment.app.Fragment
import com.ascstb.mangaapp5.model.ListType
import com.ascstb.mangaapp5.model.MangaProvidersEnum

object Session {
    var userId: String = ""
    var columnsToDisplay: Int = 3
    var selectedProvider: MangaProvidersEnum = MangaProvidersEnum.MANGATOWN
    var listType: ListType = ListType.GRID
    var currentFragment: Fragment? = null
}