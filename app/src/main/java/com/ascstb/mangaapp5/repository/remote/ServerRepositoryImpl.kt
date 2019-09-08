package com.ascstb.mangaapp5.repository.remote

import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.model.MangaProvidersEnum
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
}