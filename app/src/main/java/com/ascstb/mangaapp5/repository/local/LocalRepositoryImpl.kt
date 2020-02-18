package com.ascstb.mangaapp5.repository.local

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaProvidersEnum
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.local.database.MangaRepository
import com.ascstb.mangaapp5.repository.local.sharedPreferences.SharedPreferencesAPI
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class LocalRepositoryImpl(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesAPI: SharedPreferencesAPI
) : LocalRepository {
    override fun getMangaSourceAsync(): Deferred<RepositoryResponse<MangaProvidersEnum>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(sharedPreferencesAPI.getMangaSourceAsync().await())
            } catch (e: Exception) {
                Timber.d("LocalRepositoryImpl_TAG: getMangaSourceAsync: exception: $e")
                RepositoryResponse.Error(e)
            }
        }

    override fun setMangaSourceAsync(provider: MangaProvidersEnum): Deferred<RepositoryResponse<Boolean>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(sharedPreferencesAPI.setMangaSourceAsync(provider).await())
            } catch (e: Exception) {
                Timber.d("LocalRepositoryImpl_TAG: setMangaSourceAsync: $e")
                RepositoryResponse.Error(e)
            }
        }

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