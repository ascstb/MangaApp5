package com.ascstb.mangaapp5.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ascstb.mangaapp5.R
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.presentation.home.HomeFragment
import com.ascstb.mangaapp5.presentation.mangaDetails.MangaDetailsFragment
import timber.log.Timber

class NavigationImpl : Navigation {
    override fun goToDetails(activity: AppCompatActivity, fromFragment: Fragment, extras: Bundle?) {
        Timber.d("NavigationImpl_TAG: goToDetails: from: $fromFragment")
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
            else -> return
        }
        navigateToContent(activity, menuFragment, extras)
    }

    private fun getDetailsScreen(from: Class<*>): Fragment? = when (from) {
        HomeFragment::class.java -> MangaDetailsFragment()
        else -> null
    }

    private fun navigateToContent(
        activity: AppCompatActivity,
        fragment: Fragment,
        extras: Bundle? = null
    ) {
        Timber.d("NavigationImpl_TAG: navigateToContent: extras: $extras")
        fragment.arguments = extras

        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(fragment.tag)
            .commit()

        Session.currentFragment = fragment
    }
}