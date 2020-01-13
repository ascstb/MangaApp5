package com.ascstb.mangaapp5.repository.remote.mangaTown.reponses

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector

@Keep
class MangaDetailsResponse {
    @Selector("h1.title-top")
    var title: String = ""

    @Selector("div.detail_info > ul > li:nth-child(6) > a")
    var authors: String = ""

    @Selector("div.detail_info > ul > li:nth-child(7) > a")
    var artists: String = ""

    @Selector("div.detail_info > ul > li:nth-child(8)")
    var status: String = ""

    @Selector("ul.chapter_list > li")
    var chapterList: List<MangaChapterResponse> = listOf()
}

@Keep
class MangaChapterResponse {
    @Selector("a")
    var name: String = ""

    @Selector(value = "a", attr = "href")
    var url: String = ""

    @Selector(value = "span:nth-child(1)")
    var comment: String = ""

    @Selector(value = "span.time")
    var time: String = ""
}