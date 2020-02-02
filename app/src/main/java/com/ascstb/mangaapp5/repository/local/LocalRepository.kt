package com.ascstb.mangaapp5.repository.local

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.repository.RepositoryResponse
import kotlinx.coroutines.Deferred

interface LocalRepository {
    fun getBookmarksAsync(): Deferred<RepositoryResponse<List<Manga>>>
    fun getBookmarkAsync(manga: Manga): Deferred<RepositoryResponse<Manga?>>
    fun addBookmarkAsync(manga: Manga): Deferred<RepositoryResponse<Boolean>>
}