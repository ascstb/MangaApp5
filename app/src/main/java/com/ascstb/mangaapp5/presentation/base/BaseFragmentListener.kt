package com.ascstb.mangaapp5.presentation.base

import android.os.Parcelable

interface BaseFragmentListener {
    fun onFocused()
    fun onClicked(data: Parcelable? = null)
}