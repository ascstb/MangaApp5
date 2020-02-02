package com.ascstb.mangaapp5.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "Manga")
@Parcelize
data class Manga(
    @NonNull
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var coverUrl: String = "",
    var url: String = "",
    var authors: List<String> = listOf(),
    var artists: List<String> = listOf(),
    var status: String = "",
    var chapterList: List<MangaChapter> = listOf()
) : Parcelable