package com.ascstb.mangaapp5.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment

interface BaseFragmentListener {
    fun onFocused()
    fun onClicked(fromFragment: Fragment, extras: Bundle? = null)
}