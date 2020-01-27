package com.ascstb.mangaapp5.utils

import android.content.Context
import android.os.Parcelable
import com.ascstb.mangaapp5.presentation.base.BaseSpinnerAdapter

class GeneralSpinnerAdapter (
    ctx: Context,
    resourceId: Int,
    dataSource: List<Parcelable>?,
    private val textProp: String,
    listener: (Parcelable) -> Unit
) : BaseSpinnerAdapter<Parcelable>(
    ctx = ctx,
    resourceId = resourceId,
    dataSource = dataSource,
    listener = listener
) {
    override fun getTextProperty(): String = textProp
}