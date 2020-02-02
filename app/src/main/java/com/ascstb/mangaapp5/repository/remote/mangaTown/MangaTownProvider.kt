package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.model.MangaPage
import com.ascstb.mangaapp5.model.SearchParameters
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.ServerRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class MangaTownProvider(
    private val mangaTownAPI: MangaTownAPI
) : ServerRepository {
    private var cachedPages = mutableMapOf<String, MangaChapter>()

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
                    mangaTownAPI.getMangaDetailsAsync(formattedPath).await().let { manga ->
                        Manga(
                            title = manga.title,
                            authors = listOf(manga.authors),
                            artists = listOf(manga.artists),
                            status = manga.status
                                .replace("Status(s):", "")
                                .replace(manga.title, "")
                                .replace(
                                    "will coming soon",
                                    ""
                                ),
                            chapterList = manga.chapterList.map { chapter ->
                                MangaChapter(
                                    name = "ch. ${chapter.name.replace(manga.title, "")}",
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

    override fun getMangaPageAsync(
        path: String,
        pageNumber: Int
    ): Deferred<RepositoryResponse<MangaChapter>> = GlobalScope.async {
        try {
            val formattedPath = if (path.endsWith("/")) {
                "${path.replaceFirst(BuildConfig.API_SERVER_MANGATOWN, "")}$pageNumber.html"
            } else path

            RepositoryResponse.Ok(
                cachedPages[formattedPath]
                    ?: mangaTownAPI.getMangaPageAsync(formattedPath).await().let { response ->
                        MangaChapter(
                            pages = response.pages.filter { it.text.toIntOrNull() != null }.map {
                                MangaPage(
                                    page = "page ${it.text}",
                                    path = it.link,
                                    imageUrl = if (it.selected.isNotEmpty()) response.imageUrl.replace(
                                        "//",
                                        "http://"
                                    ) else "",
                                    imageDescription = if (it.selected.isNotEmpty()) response.imageDescription else ""
                                )
                            }.distinct()
                        )
                    }.run {
                        cachedPages[formattedPath] = this
                        this
                    })
        } catch (e: Exception) {
            Timber.d("MangaTownProvider_TAG: getMangaPageAsync: exception: ${e.message}")
            RepositoryResponse.Error(e)
        }
    }

    override fun getMangaSearchResultsAsync(
        searchParams: SearchParameters
    ): Deferred<RepositoryResponse<List<Manga>>> = GlobalScope.async {
        try {
            RepositoryResponse.Ok(mangaTownAPI.getSearchAsync(
                nameMethod = searchParams.nameMethod,
                name = searchParams.name,
                authorMethod = searchParams.authorMethod,
                author = searchParams.author,
                artistMethod = searchParams.artistMethod,
                artist = searchParams.artist,
                type = searchParams.type,
                demographic = searchParams.demographic,
                genres4Koma = searchParams.genres4Koma,
                genresAction = searchParams.genresAction,
                genresAdventure = searchParams.genresAdventure,
                genresComedy = searchParams.genresComedy,
                genresCooking = searchParams.genresCooking,
                genresDoujinshi = searchParams.genresDoujinshi,
                genresDrama = searchParams.genresDrama,
                genresEcchi = searchParams.genresEcchi,
                genresFantasy = searchParams.genresFantasy,
                genresGenderBender = searchParams.genresGenderBender,
                genresHarem = searchParams.genresHarem,
                genresHistorical = searchParams.genresHistorical,
                genresHorror = searchParams.genresHorror,
                genresJosei = searchParams.genresJosei,
                genresLolicon = searchParams.genresLolicon,
                genresMartialArts = searchParams.genresMartialArts,
                genresMature = searchParams.genresMature,
                genresMecha = searchParams.genresMecha,
                genresMusic = searchParams.genresMusic,
                genresMystery = searchParams.genresMystery,
                genresOneShot = searchParams.genresOneShot,
                genresPsychological = searchParams.genresPsychological,
                genresReverseHarem = searchParams.genresReverseHarem,
                genresRomance = searchParams.genresRomance,
                genresSchoolLife = searchParams.genresSchoolLife,
                genresSciFi = searchParams.genresSciFi,
                genresSciMFi = searchParams.genresSciMFi,
                genresSeinen = searchParams.genresSeinen,
                genresShotacon = searchParams.genresShotacon,
                genresShoujo = searchParams.genresShoujo,
                genresShoujoAi = searchParams.genresShoujoAi,
                genresShounen = searchParams.genresShounen,
                genresShounenAi = searchParams.genresShounenAi,
                genresSliceOfLife = searchParams.genresSliceOfLife,
                genresSmut = searchParams.genresSmut,
                genresSports = searchParams.genresSports,
                genresSupernatural = searchParams.genresSupernatural,
                genresSuspense = searchParams.genresSuspense,
                genresTragedy = searchParams.genresTragedy,
                genresVampire = searchParams.genresVampire,
                genresWebtoons = searchParams.genresWebtoons,
                genresYoukai = searchParams.genresYoukai,
                releasedMethod = searchParams.releasedMethod,
                released = searchParams.released,
                ratingMethod = searchParams.ratingMethod,
                rating = searchParams.rating,
                advopts = searchParams.advopts
            ).await().postList.map {
                Manga(
                    title = it.title,
                    coverUrl = it.coverUrl,
                    url = it.mangaUrl.replaceFirst("/", BuildConfig.API_SERVER_MANGATOWN)
                )
            })
        } catch (e: Exception) {
            Timber.d("MangaTownProvider_TAG: getMangaPageAsync: exception: ${e.message}")
            RepositoryResponse.Error(e)
        }
    }
}