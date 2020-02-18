package com.ascstb.mangaapp5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MangaProvidersEnum(val id: Int) : Parcelable {
    MANGATOWN(1),
    HOLYMANGA(2),
    MANGAPLUS(3),
    NOT_FOUND(0)

    ;

    companion object {
        fun fromId(id: Int) = values().firstOrNull { it.id == id } ?: NOT_FOUND
    }
}