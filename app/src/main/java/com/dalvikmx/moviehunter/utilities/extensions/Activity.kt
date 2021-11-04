package com.dalvikmx.moviehunter.utilities.extensions

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dalvikmx.moviehunter.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun AppCompatActivity.goToActivity(intent: Intent) {
    this.startActivity(intent)
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
}