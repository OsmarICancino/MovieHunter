package com.dalvikmx.moviehunter.presentation.features.billboard.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private val spaceInPixels: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spaceInPixels
        outRect.right = spaceInPixels
        outRect.bottom = spaceInPixels

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spaceInPixels
        } else {
            outRect.top = 0
        }
    }
}