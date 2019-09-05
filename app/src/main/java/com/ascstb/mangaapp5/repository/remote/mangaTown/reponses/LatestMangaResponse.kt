package com.ascstb.mangaapp5.repository.remote.mangaTown.reponses

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector

@Keep
class LatestMangaResponse {
    @Selector("ul.manga_pic_list > li")
    var postList: List<MangaResponse> = listOf()
}

@Keep
class MangaResponse {
    @Selector("p.title > a")
    var title: String = ""

    @Selector(value = "a.manga_cover > img", attr = "src")
    var coverUrl: String = ""

    @Selector(value = "a.manga_cover", attr = "href")
    var mangaUrl: String = ""

    /*@Selector("")
    var latestChapter: String = ""

    @Selector("")
    var latestChapterUrl: String = ""*/
}