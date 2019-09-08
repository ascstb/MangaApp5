package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.repository.remote.mangaTown.reponses.LatestMangaResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaTownAPI {
    @GET(BuildConfig.MANGATOWN_GET_LATEST_RELEASES)
    fun getLatestReleasesAsync(
        @Path("page") page: Int
    ): Deferred<LatestMangaResponse>
}