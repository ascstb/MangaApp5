package com.ascstb.mangaapp5.presentation.mangaDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.ascstb.mangaapp5.core.CHAPTER_DATA
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.databinding.MangaDetailsFragmentBinding
import com.ascstb.mangaapp5.model.MangaChapter
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
        vm.loading = true

        vm.onPropertyChanged(BR.availableChapters) {
            Timber.d("MangaDetailsFragment_TAG: onCreate: availableChaptersChanged")
            chapterAdapter.itemList = viewModel.recyclerItemsViewModel
            vm.loading = false
        }

        vm.onPropertyChanged(BR.bookmarked) {
            Timber.d("MangaDetailsFragment_TAG: onCreate: bookmarked: ${vm.bookmarked}")
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MangaDetailsFragmentBinding =
        MangaDetailsFragmentBinding.inflate(inflater, container, false).also { layout ->
            Timber.d("MangaDetailsFragment_TAG: inflateDataBinding: ")
            layout.btnBookmark.setOnClickListener { vm.bookmarkManga() }

            arguments?.let {
                vm.manga = it.getParcelable(MANGA_DATA)
                initRecyclerView(layout)

                vm.getDetailsAsync()
            }
        }

    private fun initRecyclerView(layout: MangaDetailsFragmentBinding) {
        chapterAdapter =
            RVChaptersAdapter { _, chapter -> onChapterClicked(chapter) }

        layout.rvChapters.apply {
            layoutManager = LinearLayoutManager(layout.root.context)
            adapter = chapterAdapter
        }

        chapterAdapter.itemList = viewModel.recyclerItemsViewModel
    }

    private fun onChapterClicked(chapter: MangaChapter) {
        Timber.d("MangaDetailsFragment_TAG: onChapterClicked: ")
        /*val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(chapter.url))
                startActivity(browserIntent)*/
        listener?.onClicked(
            fromFragment = this,
            extras = Bundle().apply {
                putParcelable(MANGA_DATA, vm.manga)
                putParcelable(CHAPTER_DATA, chapter)
            }
        )
    }
}
