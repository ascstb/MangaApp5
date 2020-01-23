package com.ascstb.mangaapp5.utils

import android.view.View
import android.widget.ScrollView
import android.widget.Spinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
    val property = instance::class.memberProperties
        // don't cast here to <Any, R>, it would succeed silently
        .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(instance) as R
}

fun ScrollView.focusOnView(focusView: View) {
    this.post { this.smoothScrollTo(0, focusView.bottom) }
}

fun Spinner.hideSpinnerDropDown() {
    try {
        val method = Spinner::class.java.getDeclaredMethod("onDetachedFromWindow")
        method.isAccessible = true
        method.invoke(this)
    } catch (e: Exception) {
        Timber.d("_TAG: hideSpinnerDropDown: exception: $e")
    }
}

@Suppress("BlockingMethodInNonBlockingContext")
fun wait(seconds: Int, afterDone: () -> Unit) = GlobalScope.launch {
    Thread.sleep((seconds * 1000).toLong())

    withContext(Dispatchers.Main) {
        afterDone()
    }
}