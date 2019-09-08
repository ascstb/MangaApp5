package com.ascstb.mangaapp5.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ascstb.mangaapp5.R
import com.ascstb.mangaapp5.presentation.base.BaseFragmentListener
import com.ascstb.mangaapp5.presentation.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), BaseFragmentListener {
    private val navigation by inject<Navigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("MainActivity_TAG: onCreate: ")

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home/*,
                R.id.navigation_dashboard,
                R.id.navigation_notifications*/
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
}
