package com.ascstb.mangaapp5.repository.local.database

import androidx.room.TypeConverter
import com.ascstb.mangaapp5.model.MangaChapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromChapterList(list: List<MangaChapter>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, MangaChapter::class.java)
        val adapter = moshi.adapter<List<MangaChapter>>(type)
        return adapter.toJson(list)
    }

    @TypeConverter
    fun toChapterList(json: String): List<MangaChapter> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, MangaChapter::class.java)
        val adapter = moshi.adapter<List<MangaChapter>>(type)
        return adapter.fromJson(json) ?: emptyList()
    }


}