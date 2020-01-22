package com.ascstb.mangaapp5.presentation.base

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ascstb.mangaapp5.model.MangaChapter

class BaseSpinnerAdapter<IT : MangaChapter>(
    ctx: Context,
    resourceId: Int,
    private val dataSource: List<IT>?
) : ArrayAdapter<IT>(ctx, resourceId) {
    override fun getCount(): Int = dataSource?.size ?: 0

    override fun getItem(position: Int): IT? = dataSource?.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val tv: TextView = super.getView(position, convertView, parent) as TextView

        tv.apply {
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
            text = dataSource?.get(position)?.name ?: ""
        }

        return tv
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val tv: TextView = super.getView(position, convertView, parent) as TextView

        tv.apply {
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
            text = dataSource?.get(position)?.name ?: ""
        }

        return tv
    }
}