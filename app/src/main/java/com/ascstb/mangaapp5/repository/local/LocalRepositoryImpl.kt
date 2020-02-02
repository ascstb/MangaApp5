package com.ascstb.mangaapp5.repository.local

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.local.database.MangaRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class LocalRepositoryImpl(
    private val mangaRepository: MangaRepository
) : LocalRepository {
    override fun getBookmarksAsync(): Deferred<RepositoryResponse<List<Manga>>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(
                    mangaRepository.getBookmarks()
                )
            } catch (e: Exception) {
                Timber.d("LocalRepositoryImpl_TAG: getBookmarksAsync: exception: ${e.message}")
                RepositoryResponse.Error(e)
            }
        }

    override fun getBookmarkAsync(manga: Manga): Deferred<RepositoryResponse<Manga?>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(
                    mangaRepository.getBookmark(manga)
                )
            } catch (e: Exception) {
                Timber.d("LocalRepositoryImpl_TAG: getBookmarksAsync: exception: ${e.message}")
                RepositoryResponse.Error(e)
            }
        }

    override fun addBookmarkAsync(manga: Manga): Deferred<RepositoryResponse<Boolean>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(
                    mangaRepository.addMangaToBookmarks(manga) > 0
                )
            } catch (e: Exception) {
                Timber.d("LocalRepositoryImpl_TAG: getBookmarksAsync: exception: ${e.message}")
                RepositoryResponse.Error(e)
            }
        }
}