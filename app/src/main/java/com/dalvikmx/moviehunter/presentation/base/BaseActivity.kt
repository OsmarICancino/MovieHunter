package com.dalvikmx.moviehunter.presentation.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dalvikmx.moviehunter.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewModel()
        setupScreen()
    }

    private fun setupScreen() {
        val window = window
        val background = ContextCompat.getDrawable(applicationContext, R.drawable.header_bg_degraded_reddark)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(applicationContext, android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)
    }
}
