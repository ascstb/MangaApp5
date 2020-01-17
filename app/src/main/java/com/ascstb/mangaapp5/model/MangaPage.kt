package com.ascstb.mangaapp5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaPage(
    var page: String = "",
    var path: String = "",
    var imageUrl: String = "",
    var imageDescription: String = ""
) : Parcelable