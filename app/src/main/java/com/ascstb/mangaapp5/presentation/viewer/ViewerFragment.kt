package com.ascstb.mangaapp5.presentation.viewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.ascstb.mangaapp5.R
import com.ascstb.mangaapp5.core.CHAPTER_DATA
import com.ascstb.mangaapp5.core.MANGA_DATA
import com.ascstb.mangaapp5.databinding.ViewerFragmentLayoutBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.utils.OnSwipeTouchListener
import com.ascstb.mangaapp5.utils.focusOnView
import com.ascstb.mangaapp5.utils.hideSpinnerDropDown
import com.ascstb.mangaapp5.utils.waitForLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ViewerFragment :
    BaseFragment<BaseFragmentListener, ViewerViewModel, ViewerFragmentLayoutBinding>() {
    private val viewModel by viewModel<ViewerViewModel>()
    private var layout: ViewerFragmentLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.availablePages) {
            vm.loading = false
            layout?.spPages?.hideSpinnerDropDown()
            layout?.spPages?.waitForLayout {
                layout?.spPages?.setSelection(vm.availablePages.indexOf(vm.currentPage), true)
                layout?.spPages?.let {
                    layout?.svContainer?.focusOnView(it)
                }
            }
        }

        vm.onPropertyChanged(BR.chapter) {
            layout?.spChapters?.hideSpinnerDropDown()
            layout?.spChapters?.waitForLayout {
                layout?.spChapters?.setSelection(vm.availableChapters.indexOf(vm.chapter), true)
                layout?.spChapters?.let {
                    layout?.svContainer?.focusOnView(it)
                }
            }
        }

        vm.onPropertyChanged(BR.imageReady) {
            Timber.d("ViewerFragment_TAG: onPropertyChanged: imageReady: ${vm.imageReady}")
            vm.imageReady = false
            layout?.spChapters?.let {
                layout?.svContainer?.focusOnView(it)
            }
        }
    }

    override fun onStop() {
        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.VISIBLE
        }
        super.onStop()
    }

    override fun onDestroy() {
        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.VISIBLE
        }
        super.onDestroy()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewerFragmentLayoutBinding =
        ViewerFragmentLayoutBinding.inflate(inflater, container, false).also { binding ->
            Timber.d("ViewerFragment_TAG: inflateDataBinding: ")
            layout = binding

            arguments?.let {
                vm.manga = it.getParcelable(MANGA_DATA)
                vm.chapter = it.getParcelable(CHAPTER_DATA)

                viewModel.loading = true
                //vm.getPageAsync()
            }

            binding.ivImage.setOnTouchListener(object : OnSwipeTouchListener(binding.root.context) {
                override fun onSwipeRight() {
                    Timber.d("ViewerFragment_TAG: onSwipeRight: ")
                    viewModel.loading = true
                    viewModel.pageNumber--
                }

                override fun onSwipeLeft() {
                    Timber.d("ViewerFragment_TAG: onSwipeLeft: ")
                    viewModel.loading = true
                    viewModel.pageNumber++
                }

                override fun onSwipeTop() {}

                override fun onSwipeBottom() {}
            })

            activity?.let {
                it.findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
            }
        }

    fun onVolumeDown() {
        Timber.d("ViewerFragment_TAG: onVolumeDown: ")
        viewModel.loading = true
        viewModel.pageNumber++
    }

    fun onVolumeUp() {
        Timber.d("ViewerFragment_TAG: onVolumeUp: ")
        viewModel.loading = true
        viewModel.pageNumber--
    }
}
