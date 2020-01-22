package com.ascstb.mangaapp5.presentation

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ascstb.mangaapp5.R
import com.ascstb.mangaapp5.core.Session
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.presentation.navigation.Navigation
import com.ascstb.mangaapp5.presentation.viewer.ViewerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), BaseFragmentListener {
    private val navigation by inject<Navigation>()
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener {
            Timber.d("MainActivity_TAG: onCreate: onNavigationItemSelected: $it")
            navigation.menuClicked(
                activity = this,
                menuTitle = Navigation.MenuTitle.fromTitle(it.title.toString())
            )

            return@setOnNavigationItemSelectedListener true
        }

        if(Session.currentFragment == null) {
            navigation.menuClicked(
                activity = this,
                menuTitle = Navigation.MenuTitle.HOME
            )
        } else {
            navigation.loadCurrentFragment(this)
        }
    }

    override fun onFocused() {
        Timber.d("MainActivity_TAG: onFocused: ")
    }

    override fun onClicked(fromFragment: Fragment, extras: Bundle?) {
        Timber.d("MainActivity_TAG: onClicked:")
        navigation.goToDetails(
            activity = this,
            fromFragment = fromFragment,
            extras = extras
        )
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (Session.currentFragment !is ViewerFragment) return super.onKeyDown(keyCode, event)

        val viewerFragment =
            Session.currentFragment?.let { it as ViewerFragment } ?: return super.onKeyDown(
                keyCode,
                event
            )

        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Timber.d("MainActivity_TAG: onKeyDown: vol down")
                viewerFragment.onVolumeDown()
                return true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Timber.d("MainActivity_TAG: onKeyDown: vol up")
                viewerFragment.onVolumeUp()
                return true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
