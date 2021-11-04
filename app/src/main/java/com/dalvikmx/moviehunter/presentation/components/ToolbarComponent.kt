package com.dalvikmx.moviehunter.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dalvikmx.moviehunter.databinding.ToolbarComponentBinding
import com.dalvikmx.moviehunter.utilities.extensions.invisible
import com.dalvikmx.moviehunter.utilities.extensions.show

class ToolbarComponent(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private val binding = ToolbarComponentBinding.inflate(LayoutInflater.from(context))
    private var mActivity: AppCompatActivity? = null

    init {
        init()
    }

    private fun init() {
        binding.apply {
            root.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            this@ToolbarComponent.addView(root)
        }
    }

    fun setup(activity: AppCompatActivity, titleToolbar: String) {
        this.mActivity = activity
        binding.run {
            btnBack.setOnClickListener {
                activity.onBackPressed()
            }
            title.text = titleToolbar.uppercase()
        }

    }

    fun backButtonVisibility(flag: Boolean) {
        binding.btnBack.run {
            if (flag) {
                show()
            } else {
                invisible()
            }
        }

    }
}
