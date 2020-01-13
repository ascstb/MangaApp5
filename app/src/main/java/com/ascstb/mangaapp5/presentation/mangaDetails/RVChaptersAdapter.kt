package com.ascstb.mangaapp5.presentation.mangaDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ascstb.mangaapp5.databinding.MangaChapterItemLayoutBinding
import com.ascstb.mangaapp5.model.MangaChapter
import com.ascstb.mangaapp5.presentation.base.BaseRVAdapter

class RVChaptersAdapter(
    listener: (View, MangaChapter) -> Unit
) : BaseRVAdapter<MangaChapter, MangaChapterViewModel, MangaChapterItemLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MangaChapterItemLayoutBinding = MangaChapterItemLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: MangaChapterViewModel): MangaChapter? = itemViewModel.chapter
}