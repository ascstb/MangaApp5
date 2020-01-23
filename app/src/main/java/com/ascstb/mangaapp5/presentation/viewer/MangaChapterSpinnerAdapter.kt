package com.ascstb.mangaapp5.presentation.viewer

import android.content.Context
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.presentation.base.BaseSpinnerAdapter

class MangaChapterSpinnerAdapter(
    ctx: Context,
    resourceId: Int,
    dataSource: List<MangaChapter>?,
    listener: (MangaChapter) -> Unit
) : BaseSpinnerAdapter<MangaChapter>(
    ctx = ctx,
    resourceId = resourceId,
    dataSource = dataSource,
    listener = listener
) {
    override fun getTextProperty(classType: MangaChapter): String = MangaChapter::name.name
}