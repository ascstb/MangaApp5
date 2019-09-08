package com.ascstb.mangaapp5.utils

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ascstb.mangaapp5.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

@BindingAdapter(value = ["imageUrl", "iconResourceName", "roundedStyle", "placeHolder"], requireAll = false)
fun imageUrl(
    view: ImageView,
    imageUrl: String?,
    iconResourceName: String?,
    roundedStyle: Boolean?,
    placeHolder: Int = 0
) {
    if (iconResourceName.isNullOrEmpty() && imageUrl.isNullOrEmpty()) {
        Timber.d("_TAG: imageUrl: no image available, $placeHolder")
        return
    }

    try {
        if (!imageUrl.isNullOrEmpty()) {
            if (roundedStyle == true) {
                Glide.with(view).load(imageUrl).apply(
                    RequestOptions.bitmapTransform(
                        RoundedCorners(
                            view.resources.getDimension(
                                R.dimen.cornerRoundedRadius
                            ).toInt()
                        )
                    )
                ).into(view)
            } else {
                Glide.with(view).load(imageUrl).into(view)
            }
        } else if (!iconResourceName.isNullOrEmpty()) {
            val drawableId = view.context.getDrawable(
                view.context.resources.getIdentifier(
                    iconResourceName,
                    "drawable",
                    view.context.packageName
                )
            )
            Glide.with(view).load(drawableId).into(view)
        }
    } catch (e: Exception) {
        Timber.d("ViewBindingAdapter_TAG: iconUrl: $imageUrl, iconResourceName: $iconResourceName")
    }
}

@BindingAdapter(value = ["htmlContent"], requireAll = false)
fun htmlContent(tv: TextView, htmlContent: String?) {
    tv.text = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT)
}