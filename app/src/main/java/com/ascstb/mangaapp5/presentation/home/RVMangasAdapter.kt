package com.ascstb.mangaapp5.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ascstb.mangaapp5.databinding.MangaItemLayoutBinding
import com.ascstb.mangaapp5.model.Manga
import com.ascstb.mangaapp5.presentation.base.BaseRVAdapter

class RVMangasAdapter (
    listener: (View, Manga) -> Unit
) : BaseRVAdapter<Manga, MangaItemViewModel, MangaItemLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MangaItemLayoutBinding =
        MangaItemLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: MangaItemViewModel): Manga? = itemViewModel.manga
}