package com.ascstb.mangaapp5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaChapter(
    var name: String = "",
    var url: String = "",
    var comment: String = "",
    var updatedAt: String = "",
    var pages: List<MangaPage> = emptyList()
) : Parcelable