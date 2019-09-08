package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class MangaTownProvider (
    private val mangaTownAPI: MangaTownAPI
) : ServerRepository {
    override fun getLatestReleasesAsync(page: Int): Deferred<RepositoryResponse<List<Manga>>> = GlobalScope.async {
        try {
            RepositoryResponse.Ok(
                mangaTownAPI.getLatestReleasesAsync(page).await().postList.map {
                    Manga(
                        title = it.title,
                        coverUrl = it.coverUrl,
                        url = it.mangaUrl
                    )
                }
            )
        } catch (e: Exception) {
            Timber.d("ServerRepositoryImpl_TAG: getLatestReleasesAsync: exception: ${e.message}")
            RepositoryResponse.Error(e)
        }
    }
}