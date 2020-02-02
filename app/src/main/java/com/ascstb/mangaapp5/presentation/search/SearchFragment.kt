package com.ascstb.mangaapp5.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.databinding.SearchFragmentLayoutBinding
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.presentation.home.RVMangasAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchFragment : BaseFragment<BaseFragmentListener, SearchViewModel, SearchFragmentLayoutBinding>() {
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var layout: SearchFragmentLayoutBinding
    private lateinit var resultsAdapter: RVMangasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.searchResults) {
            Timber.d("SearchFragment_TAG: onCreate: onSearchResultsChanged: ${vm.searchResults.size}")
            resultsAdapter.itemList = viewModel.recyclerItemsViewModel
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SearchFragmentLayoutBinding = SearchFragmentLayoutBinding.inflate(inflater, container, false).also {
        layout = it
        initRecyclerView()

        layout.btnSearch.setOnClickListener {
            vm.loading = true
            vm.getResultsAsync()

            activity?.let { act ->
                val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    private fun initRecyclerView() {
        resultsAdapter = RVMangasAdapter{_, manga -> onMangaClicked(manga)}

        layout.rvResults.apply {
            layoutManager = GridLayoutManager(context, Session.columnsToDisplay)
            adapter = resultsAdapter
        }

        resultsAdapter.itemList = viewModel.recyclerItemsViewModel
    }

    private fun onMangaClicked(manga: Manga) {
        Timber.d("SearchFragment_TAG: onMangaClicked: ")
        listener?.onClicked(
            fromFragment = this,
            extras = Bundle().apply {
                putParcelable(MANGA_DATA, manga)
            }
        )
    }
}
