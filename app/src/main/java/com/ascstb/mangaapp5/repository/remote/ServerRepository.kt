package com.ascstb.mangaapp5.repository.remote

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.repository.RepositoryResponse
import kotlinx.coroutines.Deferred

interface ServerRepository {
    fun getLatestReleasesAsync(page: Int): Deferred<RepositoryResponse<List<Manga>>>
    fun getMangaDetailsAsync(path: String): Deferred<RepositoryResponse<Manga>>
    fun getMangaPageAsync(path: String, pageNumber: Int): Deferred<RepositoryResponse<MangaChapter>>
}