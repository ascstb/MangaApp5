package com.ascstb.mangaapp5.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*

interface Navigation {
    fun menuClicked(activity: AppCompatActivity, menuTitle: MenuTitle, extras: Bundle? = null)
    fun goToDetails(activity: AppCompatActivity, fromFragment: Fragment, extras: Bundle? = null)
    fun loadCurrentFragment(activity: AppCompatActivity)

    enum class MenuTitle(val title: String) {
        HOME("home"),
        ADVANCE_SEARCH("advance search"),
        FAVORITES("favorites"),
        NOT_FOUND("")
        ;

        companion object {
            fun fromTitle(title: String) = values().firstOrNull {
                it.title == title.toLowerCase(Locale.US)
            } ?: NOT_FOUND
        }
    }
}