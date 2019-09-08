package com.ascstb.mangaapp5.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface Navigation {
    fun goToDetails(activity: AppCompatActivity, fromFragment: Fragment, extras: Bundle? = null)
}