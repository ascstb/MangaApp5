package com.ascstb.mangaapp5.presentation.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.databinding.BookmarksFragmentLayoutBinding
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.presentation.home.RVMangasAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BookmarksFragment :
    BaseFragment<BaseFragmentListener, BookmarksViewModel, BookmarksFragmentLayoutBinding>() {
    private val viewModel by viewModel<BookmarksViewModel>()
    private var layout: BookmarksFragmentLayoutBinding? = null
    private lateinit var mangasAdapter: RVMangasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel
        vm.loading = true

        vm.onPropertyChanged(BR.availableMangas) {
            Timber.d("BookmarksFragment_TAG: onCreate: onAvailableMangasChanged: ${vm.availableMangas.size}")
            vm.loading = false
            mangasAdapter.itemList = vm.recyclerViewItemViewModel
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BookmarksFragmentLayoutBinding =
        BookmarksFragmentLayoutBinding.inflate(inflater, container, false).also {
            layout = it
            initRecyclerView()

            vm.getBookmarksAsync()
        }

    private fun initRecyclerView() {
        mangasAdapter = RVMangasAdapter { _, manga -> onMangaClicked(manga) }
        layout?.let {
            it.rvBookmarks.apply {
                layoutManager = GridLayoutManager(it.root.context, Session.columnsToDisplay)
                adapter = mangasAdapter
            }
        }

        mangasAdapter.itemList = vm.recyclerViewItemViewModel
    }

    private fun onMangaClicked(manga: Manga) {
        Timber.d("BookmarksFragment_TAG: onMangaClicked: ")
        listener?.onClicked(
            fromFragment = this,
            extras = Bundle().apply {
                putParcelable(MANGA_DATA, manga)
            })
    }
}
