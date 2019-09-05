package com.ascstb.mangaapp5.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ascstb.mangaapp5.BR
import com.ascstb.mangaapp5.databinding.FragmentHomeBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<BaseFragmentListener, HomeViewModel, FragmentHomeBinding>() {

    /*private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("HomeFragment_TAG: onCreateView: ")

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }*/
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel

        vm.onPropertyChanged(BR.latestManga) {
            Timber.d("HomeFragment_TAG: onCreate: onLatestMangaChanged: ${vm.latestManga.size}")
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false).also { layout ->
            vm.text.observe(this, Observer {
                layout.textHome.text = it
            })

            vm.getLatestMangaAsync()
        }
}