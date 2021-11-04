package com.dalvikmx.moviehunter.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.dalvikmx.moviehunter.R
import com.dalvikmx.moviehunter.databinding.ContentNavigationBinding
import com.dalvikmx.moviehunter.presentation.features.billboard.BillboardViewModel


class BottomNavigationComponent(context: Context, attrs: AttributeSet) :
    RelativeLayout(context, attrs) {
    private val binding = ContentNavigationBinding.inflate(LayoutInflater.from(context))
    init {
        init(context)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.content_navigation, this)
        binding.apply {
            root.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            this@BottomNavigationComponent.addView(root)
        }
    }

    fun setup(viewModel: BillboardViewModel) {
        binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popularMovies -> {
                    viewModel.getPopularList()
                    return@setOnItemSelectedListener true
                }
                R.id.highAudience -> {
                    viewModel.getTopRatedList()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

}
