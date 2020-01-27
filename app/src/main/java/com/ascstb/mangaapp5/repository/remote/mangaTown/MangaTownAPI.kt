package com.ascstb.mangaapp5.repository.remote.mangaTown

import com.ascstb.mangaapp5.BuildConfig
import com.ascstb.mangaapp5.repository.remote.mangaTown.reponses.LatestMangaResponse
import com.ascstb.mangaapp5.repository.remote.mangaTown.reponses.MangaDetailsResponse
import com.ascstb.mangaapp5.repository.remote.mangaTown.reponses.MangaPageResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaTownAPI {
    @GET(BuildConfig.MANGATOWN_GET_LATEST_RELEASES)
    fun getLatestReleasesAsync(
        @Path("page") page: Int
    ): Deferred<LatestMangaResponse>

    @GET(BuildConfig.MANGATOWN_GET_MANGA_DETAILS)
    fun getMangaDetailsAsync(
        @Path("path") path: String
    ): Deferred<MangaDetailsResponse>

    @GET(BuildConfig.MANGATOWN_GET_PAGE)
    fun getMangaPageAsync(
        @Path("path") path: String
    ): Deferred<MangaPageResponse>

    @GET(BuildConfig.MANGATOWN_GET_SEARCH_RESULTS)
    fun getSearchAsync(
        @Query("name_method") nameMethod: String,
        @Query("name") name: String,
        @Query("author_method") authorMethod: String,
        @Query("author") author: String,
        @Query("artist_method") artistMethod: String,
        @Query("artist") artist: String,
        @Query("type") type: String,
        @Query("demographic") demographic: String,
        @Query("genres[4+Koma]") genres4Koma: Int,
        @Query("genres[Action]") genresAction: Int,
        @Query("genres[Adventure]") genresAdventure: Int,
        @Query("genres[Comedy]") genresComedy: Int,
        @Query("genres[Cooking]") genresCooking: Int,
        @Query("genres[Doujinshi]") genresDoujinshi: Int,
        @Query("genres[Drama]") genresDrama: Int,
        @Query("genres[Ecchi]") genresEcchi: Int,
        @Query("genres[Fantasy]") genresFantasy: Int,
        @Query("genres[Gender+Bender]") genresGenderBender: Int,
        @Query("genres[Harem]") genresHarem: Int,
        @Query("genres[Historical]") genresHistorical: Int,
        @Query("genres[Horror]") genresHorror: Int,
        @Query("genres[Josei]") genresJosei: Int,
        @Query("genres[Lolicon]") genresLolicon: Int,
        @Query("genres[Martial+Arts]") genresMartialArts: Int,
        @Query("genres[Mature]") genresMature: Int,
        @Query("genres[Mecha]") genresMecha: Int,
        @Query("genres[Music]") genresMusic: Int,
        @Query("genres[Mystery]") genresMystery: Int,
        @Query("genres[One+Shot]") genresOneShot: Int,
        @Query("genres[Psychological]") genresPsychological: Int,
        @Query("genres[Reverse+Harem]") genresReverseHarem: Int,
        @Query("genres[Romance]") genresRomance: Int,
        @Query("genres[School+Life]") genresSchoolLife: Int,
        @Query("genres[Sci+Fi]") genresSciFi: Int,
        @Query("genres[Sci-fi]") genresSciMFi: Int,
        @Query("genres[Seinen]") genresSeinen: Int,
        @Query("genres[Shotacon]") genresShotacon: Int,
        @Query("genres[Shoujo]") genresShoujo: Int,
        @Query("genres[Shoujo+Ai]") genresShoujoAi: Int,
        @Query("genres[Shounen]") genresShounen: Int,
        @Query("genres[Shounen+Ai]") genresShounenAi: Int,
        @Query("genres[Slice+Of+Life]") genresSliceOfLife: Int,
        @Query("genres[Smut]") genresSmut: Int,
        @Query("genres[Sports]") genresSports: Int,
        @Query("genres[Supernatural]") genresSupernatural: Int,
        @Query("genres[Suspense]") genresSuspense: Int,
        @Query("genres[Tragedy]") genresTragedy: Int,
        @Query("genres[Vampire]") genresVampire: Int,
        @Query("genres[Webtoons]") genresWebtoons: Int,
        @Query("genres[Youkai]") genresYoukai: Int,
        @Query("released_method") releasedMethod: String,
        @Query("released") released: String,
        @Query("rating_method") ratingMethod: String,
        @Query("rating") rating: String,
        @Query("advopts") advopts: String
    ): Deferred<LatestMangaResponse>
}