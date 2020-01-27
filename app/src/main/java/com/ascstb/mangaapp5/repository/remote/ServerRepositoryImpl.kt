package com.ascstb.mangaapp5.repository.remote

import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.model.MangaProvidersEnum
import com.ascstb.mangaapp5.model.SearchParameters
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.remote.mangaTown.MangaTownProvider
import kotlinx.coroutines.Deferred

class ServerRepositoryImpl(
    private val mangaTownProvider: MangaTownProvider
) : ServerRepository {
    override fun getLatestReleasesAsync(page: Int): Deferred<RepositoryResponse<List<Manga>>> =
        when (Session.selectedProvider) {
            MangaProvidersEnum.MANGATOWN -> mangaTownProvider.getLatestReleasesAsync(page)
        }

    override fun getMangaDetailsAsync(path: String): Deferred<RepositoryResponse<Manga>> =
        when (Session.selectedProvider) {
            MangaProvidersEnum.MANGATOWN -> mangaTownProvider.getMangaDetailsAsync(path)
        }

    override fun getMangaPageAsync(
        path: String,
        pageNumber: Int
    ): Deferred<RepositoryResponse<MangaChapter>> = when (Session.selectedProvider) {
        MangaProvidersEnum.MANGATOWN -> mangaTownProvider.getMangaPageAsync(path, pageNumber)
    }

    override fun getMangaSearchResultsAsync(searchParams: SearchParameters): Deferred<RepositoryResponse<List<Manga>>> = when(Session.selectedProvider) {
        MangaProvidersEnum.MANGATOWN -> mangaTownProvider.getMangaSearchResultsAsync(searchParams)
    }
}