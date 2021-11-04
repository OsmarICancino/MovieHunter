package com.dalvikmx.moviehunter.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.dalvikmx.moviehunter.R

object ImageUtils {
    fun getPlaceHolder(context: Context): Drawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(
            ContextCompat.getColor(
                context,
                R.color.primary_blue
            )
        )
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}