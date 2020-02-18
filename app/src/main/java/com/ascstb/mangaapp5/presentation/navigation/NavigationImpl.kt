package com.ascstb.mangaapp5.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ascstb.mangaapp5.R
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.presentation.bookmarks.BookmarksFragment
import com.ascstb.mangaapp5.presentation.home.FilterFragment
import com.ascstb.mangaapp5.presentation.home.HomeFragment
import com.ascstb.mangaapp5.presentation.mangaDetails.MangaDetailsFragment
import com.ascstb.mangaapp5.presentation.search.SearchFragment
import com.ascstb.mangaapp5.presentation.settings.SettingsFragment
import com.ascstb.mangaapp5.presentation.viewer.ViewerFragment
import timber.log.Timber
import java.util.*

@ExperimentalStdlibApi
class NavigationImpl : Navigation {
    override fun goToDetails(activity: AppCompatActivity, fromFragment: Fragment, extras: Bundle?) {
        Timber.d("NavigationImpl_TAG: goToDetails: from: ${fromFragment::class.java.simpleName}")
        val detailsScreen = getDetailsScreen(fromFragment::class.java) ?: return

        navigateToContent(activity, detailsScreen, extras)
    }

    override fun menuClicked(
        activity: AppCompatActivity,
        menuTitle: Navigation.MenuTitle,
        extras: Bundle?
    ) {
        Timber.d("NavigationImpl_TAG: menuClicked: $menuTitle")
        val menuFragment = when (menuTitle) {
            Navigation.MenuTitle.HOME,
            Navigation.MenuTitle.ADVANCE_SEARCH,
            Navigation.MenuTitle.FAVORITES -> HomeFragment()
            Navigation.MenuTitle.SEARCH -> SearchFragment()
            Navigation.MenuTitle.BOOKMARKS -> BookmarksFragment()
            Navigation.MenuTitle.SETTINGS -> SettingsFragment()
            else -> return
        }
        navigateToContent(activity, menuFragment, extras, menuTitle.title)
    }

    override fun goToTools(
        activity: AppCompatActivity,
        toolbarMenu: Navigation.ToolbarMenu,
        extras: Bundle?
    ) {
        Timber.d("NavigationImpl_TAG: goToTools: $toolbarMenu")
        val toolbarFragment = when (toolbarMenu) {
            Navigation.ToolbarMenu.FILTER -> FilterFragment()
            else -> return
        }
        navigateToContent(activity, toolbarFragment, extras, toolbarMenu.title)
    }

    override fun loadCurrentFragment(activity: AppCompatActivity) {
        Session.currentFragment?.let {
            navigateToContent(activity, it, it.arguments)
        }
    }

    private fun getDetailsScreen(from: Class<*>): Fragment? = when (from) {
        HomeFragment::class.java,
        BookmarksFragment::class.java,
        SearchFragment::class.java -> MangaDetailsFragment()
        MangaDetailsFragment::class.java -> ViewerFragment()
        else -> null
    }

    private fun navigateToContent(
        activity: AppCompatActivity,
        fragment: Fragment,
        extras: Bundle? = null,
        title: String = ""
    ) {
        fragment.arguments = extras

        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(fragment.tag)
            .commit()

        activity.supportActionBar?.title = title.capitalize(Locale.US)

        Session.currentFragment = fragment
    }
}