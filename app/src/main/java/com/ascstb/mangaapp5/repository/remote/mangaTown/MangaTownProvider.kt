package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class MangaTownProvider(
    private val mangaTownAPI: MangaTownAPI
) : ServerRepository {
    override fun getLatestReleasesAsync(page: Int): Deferred<RepositoryResponse<List<Manga>>> =
        GlobalScope.async {
            try {
                RepositoryResponse.Ok(
                    mangaTownAPI.getLatestReleasesAsync(page).await().postList.map {
                        Manga(
                            title = it.title,
                            coverUrl = it.coverUrl,
                            url = it.mangaUrl.replaceFirst("/", BuildConfig.API_SERVER_MANGATOWN)
                        )
                    }
                )
            } catch (e: Exception) {
                Timber.d("ServerRepositoryImpl_TAG: getLatestReleasesAsync: exception: ${e.message}")
                RepositoryResponse.Error(e)
            }
        }

    override fun getMangaDetailsAsync(path: String): Deferred<RepositoryResponse<Manga>> =
        GlobalScope.async {
            try {
                val formattedPath = path.replaceFirst(BuildConfig.API_SERVER_MANGATOWN, "")
                RepositoryResponse.Ok(
                    mangaTownAPI.getMangaDetailsAsync(formattedPath).await().let {
                        Manga(
                            title = it.title,
                            authors = listOf(it.authors),
                            artists = listOf(it.artists),
                            status = it.status
                                .replace("Status(s):", "")
                                .replace(it.title, "")
                                .replace(
                                    "will coming soon",
                                    ""
                                ),
                            chapterList = it.chapterList.map { chapter ->
                                MangaChapter(
                                    name = chapter.name,
                                    url = chapter.url.replaceFirst(
                                        "/",
                                        BuildConfig.API_SERVER_MANGATOWN
                                    ),
                                    comment = chapter.comment,
                                    updatedAt = chapter.time
                                )
                            }
                        )
                    }
                )
            } catch (e: Exception) {
                Timber.d("MangaTownProvider_TAG: getMangaDetailsAsync: exception: ${e.message}")
                RepositoryResponse.Error(e)
            }
        }
}