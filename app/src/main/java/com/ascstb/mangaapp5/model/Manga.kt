package com.ascstb.mangaapp5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manga(
    var title: String,
    var coverUrl: String,
    var url: String
) : Parcelable