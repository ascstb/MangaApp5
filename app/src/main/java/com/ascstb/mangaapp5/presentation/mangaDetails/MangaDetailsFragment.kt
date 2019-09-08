package com.ascstb.mangaapp5.presentation.mangaDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.databinding.MangaDetailsFragmentBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaDetailsFragment :
    BaseFragment<BaseFragmentListener, MangaDetailsViewModel, MangaDetailsFragmentBinding>() {
    private val viewModel by viewModel<MangaDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MangaDetailsFragmentBinding =
        MangaDetailsFragmentBinding.inflate(inflater, container, false).also { layout ->
            arguments?.let {
                vm.manga = it.getParcelable(MANGA_DATA)
            }
        }
}
