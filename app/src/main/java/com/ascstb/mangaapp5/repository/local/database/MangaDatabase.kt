package com.ascstb.mangaapp5.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ascstb.mangaapp5.model.Manga

@Database(entities = [Manga::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MangaDatabase : RoomDatabase() {
    abstract fun mangaDAO(): MangaDAO
}