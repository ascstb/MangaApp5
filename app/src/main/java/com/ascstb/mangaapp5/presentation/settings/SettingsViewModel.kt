package com.ascstb.mangaapp5.presentation.settings

import android.os.Parcelable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.model.MangaProvidersEnum
import com.ascstb.mangaapp5.presentation.base.BaseViewModel
import com.ascstb.mangaapp5.repository.RepositoryResponse
import com.ascstb.mangaapp5.repository.local.LocalRepository
import com.ascstb.mangaapp5.utils.runOnResult
import timber.log.Timber

class SettingsViewModel(
    private val repository: LocalRepository
) : BaseViewModel() {
    var availableSources = emptyList<MangaProvidersEnum>()

    @get:Bindable
    var provider: MangaProvidersEnum? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.provider)
            notifyChange()
        }

    var sourceTextProp: String = MangaProvidersEnum::name.name

    val onSourceChanged = fun(itemClicked: Parcelable) {
        val sourceSelected = itemClicked as MangaProvidersEnum
        Timber.d("SettingsViewModel_TAG: sourceChanged: $sourceSelected")
        provider = sourceSelected
    }

    fun getMangaSourceAsync() = background {
        Timber.d("SettingsViewModel_TAG: getMangaSourceAsync: ")
        repository.getMangaSourceAsync().runOnResult {
            when (this) {
                is RepositoryResponse.Error -> Timber.d("SettingsViewModel_TAG: getMangaSourceAsync: ")
                is RepositoryResponse.Ok -> provider =
                    if (result == MangaProvidersEnum.NOT_FOUND) Session.selectedProvider else result
            }
        }
    }

    fun setMangaSourceAsync() = background {
        Timber.d("SettingsViewModel_TAG: setMangaSourceAsync: ")
        provider?.let {
            repository.setMangaSourceAsync(it).runOnResult {
                when (this) {
                    is RepositoryResponse.Error -> Timber.d("SettingsViewModel_TAG: setMangaSourceAsync: ")
                    is RepositoryResponse.Ok -> Timber.d("SettingsViewModel_TAG: setMangaSourceAsync: result: $result")
                }
            }
        }
    }
}