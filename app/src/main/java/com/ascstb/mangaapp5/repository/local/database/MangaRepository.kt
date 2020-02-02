package com.ascstb.mangaapp5.repository.local.database

import android.content.Context
import androidx.room.Room
import com.ascstb.mangaapp5.model.Manga

class MangaRepository(context: Context) {
    private val mangaDAO: MangaDAO

    init {
        val mangaDatabase =
            Room.databaseBuilder(context, MangaDatabase::class.java, "manga-database").build()
        mangaDAO = mangaDatabase.mangaDAO()
    }

    fun getBookmarks(): List<Manga> = mangaDAO.getBookmarks()

    fun getBookmark(manga: Manga): Manga? = mangaDAO.getBookmark(mangaUrl = manga.url)

    fun addMangaToBookmarks(manga: Manga) = mangaDAO.addManga(manga)

    fun deleteMangaFromBookmarks(mangaId: String) = mangaDAO.deleteManga(mangaId)
}