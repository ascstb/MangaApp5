package com.ascstb.mangaapp5.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ascstb.mangaapp5.databinding.FilterFragmentLayoutBinding
import com.ascstb.mangaapp5.presentation.base.BaseFragment
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterFragment :
    BaseFragment<BaseFragmentListener, FilterViewModel, FilterFragmentLayoutBinding>() {
    private val viewModel by viewModel<FilterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FilterFragmentLayoutBinding = FilterFragmentLayoutBinding.inflate(inflater, container, false)

}
