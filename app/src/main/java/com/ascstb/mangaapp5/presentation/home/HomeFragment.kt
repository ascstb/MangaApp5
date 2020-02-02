package com.ascstb.mangaapp5.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.databinding.FragmentHomeBinding
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<BaseFragmentListener, HomeViewModel, FragmentHomeBinding>() {
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var mangaAdapter: RVMangasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.latestManga) {
            Timber.d("HomeFragment_TAG: onCreate: onLatestMangaChanged: ${vm.latestManga.size}")
            binding?.swipeRefresh?.isRefreshing = false
            mangaAdapter.itemList = viewModel.recyclerItemsViewModel
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false).also { layout ->
            initRecyclerView(layout)
            bindScrollListener(layout)
            bindRefreshListener(layout)

            vm.getLatestMangaAsync()
        }

    private fun initRecyclerView(layout: FragmentHomeBinding) {
        mangaAdapter = RVMangasAdapter { _, manga -> onMangaClicked(manga)}

        layout.rvMangas.apply {
            layoutManager = GridLayoutManager(context, Session.columnsToDisplay)
            adapter = mangaAdapter
        }

        mangaAdapter.itemList = viewModel.recyclerItemsViewModel
    }

    private fun bindScrollListener(layout: FragmentHomeBinding) {
        layout.rvMangas.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!layout.rvMangas.canScrollVertically(RecyclerView.SCROLL_AXIS_VERTICAL)) {
                viewModel.endOfScroll()
            }
        }
    }

    private fun bindRefreshListener(layout: FragmentHomeBinding) {
        layout.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun onMangaClicked(manga: Manga) {
        Timber.d("HomeFragment_TAG: onMangaClicked: ${manga.title}")
        listener?.onClicked(
            fromFragment = this,
            extras = Bundle().apply {
                putParcelable(MANGA_DATA, manga)
            })
    }
}