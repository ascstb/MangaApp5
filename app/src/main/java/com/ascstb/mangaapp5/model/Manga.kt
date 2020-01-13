package com.ascstb.mangaapp5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manga(
    var title: String = "",
    var coverUrl: String = "",
    var url: String = "",
    var authors: List<String> = listOf(),
    var artists: List<String> = listOf(),
    var status: String = "",
    var chapterList: List<MangaChapter> = listOf()
) : Parcelable