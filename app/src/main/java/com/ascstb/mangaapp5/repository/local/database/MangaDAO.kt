package com.ascstb.mangaapp5.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ascstb.mangaapp5.model.Manga

@Dao
interface MangaDAO {
    @Query("SELECT * FROM Manga")
    fun getBookmarks(): List<Manga>

    @Query("SELECT * FROM Manga AS a WHERE a.url = :mangaUrl")
    fun getBookmark(mangaUrl: String): Manga?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addManga(manga: Manga) : Long

    @Query("DELETE FROM Manga WHERE id = :mangaId")
    fun deleteManga(mangaId: String)
}