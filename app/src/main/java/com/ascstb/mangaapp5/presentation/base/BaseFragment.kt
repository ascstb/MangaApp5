package com.ascstb.mangaapp5.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ascstb.mangaapp5.BR

abstract class BaseFragment<L : BaseFragmentListener, VM : BaseViewModel, VDB : ViewDataBinding> :
    Fragment() {
    lateinit var vm: VM
    protected var binding: VDB? = null
    protected lateinit var lInflater: LayoutInflater
    protected var listener: L? = null

    var customTag = this.tag


    override fun onAttach(context: Context) {
        super.onAttach(context)

        @Suppress("UNCHECKED_CAST")
        if (context is BaseFragmentListener) {
            listener = context as L
        } else {
            throw Exception("Listener not implemented on Activity")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflateDataBinding(inflater, container).run {
            setVariable(BR.viewModel, vm)
            lInflater = inflater

            executePendingBindings()
            binding = this
        }

        return binding?.root
    }

    protected abstract fun inflateDataBinding(inflater: LayoutInflater, container: ViewGroup?): VDB
}