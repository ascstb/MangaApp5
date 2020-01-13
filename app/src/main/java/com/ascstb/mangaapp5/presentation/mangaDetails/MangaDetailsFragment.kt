package com.ascstb.mangaapp5.presentation.mangaDetails


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.databinding.MangaDetailsFragmentBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MangaDetailsFragment :
    BaseFragment<BaseFragmentListener, MangaDetailsViewModel, MangaDetailsFragmentBinding>() {
    private val viewModel by viewModel<MangaDetailsViewModel>()
    private lateinit var chapterAdapter: RVChaptersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.availableChapters) {
            Timber.d("MangaDetailsFragment_TAG: onCreate: availableChaptersChanged")
            chapterAdapter.itemList = viewModel.recyclerItemsViewModel
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MangaDetailsFragmentBinding =
        MangaDetailsFragmentBinding.inflate(inflater, container, false).also { layout ->
            arguments?.let {
                vm.manga = it.getParcelable(MANGA_DATA)
                initRecyclerView(layout)

                vm.getDetailsAsync()
            }
        }

    private fun initRecyclerView(layout: MangaDetailsFragmentBinding) {
        chapterAdapter =
            RVChaptersAdapter { _, chapter ->
                Timber.d("MangaDetailsFragment_TAG: initRecyclerView: onChapterClicked: ${chapter.url}")
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(chapter.url))
                startActivity(browserIntent)
            }

        layout.rvChapters.apply {
            layoutManager = LinearLayoutManager(layout.root.context)
            adapter = chapterAdapter
        }

        chapterAdapter.itemList = viewModel.recyclerItemsViewModel
    }
}
