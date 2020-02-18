package com.ascstb.mangaapp5.repository.local.sharedPreferences

import com.ascstb.mangaapp5.model.MangaProvidersEnum
import kotlinx.coroutines.Deferred

interface SharedPreferencesAPI {
    fun getMangaSourceAsync(): Deferred<MangaProvidersEnum>
    fun setMangaSourceAsync(provider: MangaProvidersEnum): Deferred<Boolean>
}