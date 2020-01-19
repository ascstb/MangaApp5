package com.ascstb.mangaapp5.repository.remote.mangaTown.reponses

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector

@Keep
class MangaPageResponse {
    @Selector(value = "#top_chapter_list > option")
    var chapters: List<OptionResponse> = emptyList()

    @Selector(value = "div.page_select > select > option")
    var pages: List<OptionResponse> = emptyList()

    @Selector(value = "#image", attr = "src")
    var imageUrl: String = ""

    @Selector(value = "#image", attr = "alt")
    var imageDescription: String = ""
}

@Keep
class OptionResponse {
    @Selector(value = "*")
    var text: String = ""

    @Selector(value = "*", attr = "value")
    var link: String = ""

    @Selector(value = "*", attr = "selected")
    var selected: String = ""
}