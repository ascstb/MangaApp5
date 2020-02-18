package com.ascstb.mangaapp5.repository.local.sharedPreferences

import android.content.Context
import com.ascstb.mangaapp5.core.SHARED_PREFERENCES
import com.ascstb.mangaapp5.core.SHARED_PREFERENCES_PROVIDER
import com.ascstb.mangaapp5.model.MangaProvidersEnum
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class SharedPreferencesImpl(
    private val appCtx: Context
) : SharedPreferencesAPI {
    override fun getMangaSourceAsync(): Deferred<MangaProvidersEnum> = GlobalScope.async {
        Timber.d("SharedPreferencesImpl_TAG: getMangaSourceAsync: ")
        try {
            val sp = appCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val providerId = sp.getInt(SHARED_PREFERENCES_PROVIDER, 0)
            Timber.d("SharedPreferencesImpl_TAG: getMangaSourceAsync: providerId: $providerId")
            return@async MangaProvidersEnum.fromId(providerId)

        } catch (e: Exception) {
            Timber.d("SharedPreferencesImpl_TAG: getMangaSourceAsync: exception: $e")
        }

        return@async MangaProvidersEnum.NOT_FOUND
    }

    override fun setMangaSourceAsync(provider: MangaProvidersEnum) = GlobalScope.async {
        Timber.d("SharedPreferencesImpl_TAG: setMangaSourceAsync: $provider")
        try {
            val sp = appCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            with(sp.edit()) {
                putInt(SHARED_PREFERENCES_PROVIDER, provider.id)
                return@async commit()
            }
        } catch (e: Exception) {
            Timber.d("SharedPreferencesImpl_TAG: setMangaSourceAsync: exception: $e")
        }

        return@async false
    }
}