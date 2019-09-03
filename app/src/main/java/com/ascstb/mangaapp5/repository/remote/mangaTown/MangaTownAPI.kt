package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.repository.remote.mangaTown.reponses.LatestMangaResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MangaTownAPI {
    @GET(BuildConfig.MANGATOWN_GET_LATEST_RELEASES)
    fun getLatestReleasesAsync() : Deferred<LatestMangaResponse>
}