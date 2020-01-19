package com.ascstb.mangaapp5.presentation.viewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ascstb.mangaapp5.core.CHAPTER_DATA
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.databinding.ViewerFragmentLayoutBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ViewerFragment : BaseFragment<BaseFragmentListener, ViewerViewModel, ViewerFragmentLayoutBinding>() {
    private val viewModel by viewModel<ViewerViewModel>()
    private lateinit var layout: ViewerFragmentLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewerFragmentLayoutBinding = ViewerFragmentLayoutBinding.inflate(inflater, container, false).also { binding ->
        Timber.d("ViewerFragment_TAG: inflateDataBinding: ")
        layout = binding

        arguments?.let {
            vm.manga = it.getParcelable(MANGA_DATA)
            vm.chapter = it.getParcelable(CHAPTER_DATA)

            vm.getPageAsync()
        }
    }
}
