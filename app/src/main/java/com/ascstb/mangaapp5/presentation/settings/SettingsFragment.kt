package com.ascstb.mangaapp5.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.databinding.SettingsFragmentBinding
import com.ascstb.mangaapp5.model.MangaProvidersEnum
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.utils.hideSpinnerDropDown
import com.ascstb.mangaapp5.utils.waitForLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SettingsFragment : BaseFragment<BaseFragmentListener, SettingsViewModel, SettingsFragmentBinding>() {
    private val viewModel by viewModel<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.provider) {
            Timber.d("SettingsFragment_TAG: onCreate: onProviderChanged: ${vm.provider}")
            binding?.spSource?.hideSpinnerDropDown()
            binding?.spSource?.waitForLayout {
                vm.provider?.let {
                    binding?.spSource?.setSelection(MangaProvidersEnum.values().indexOf(it), true)
                }
            }
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SettingsFragmentBinding = SettingsFragmentBinding.inflate(inflater, container, false).also {
        Timber.d("SettingsFragment_TAG: inflateDataBinding: ")

        vm.availableSources = MangaProvidersEnum.values().toList()
        vm.getMangaSourceAsync()
    }
}
