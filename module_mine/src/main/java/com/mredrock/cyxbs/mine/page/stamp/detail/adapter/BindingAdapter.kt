package com.mredrock.cyxbs.mine.page.stamp.detail.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  22:57
 *@signature 我们不明前路，却已在路上
 */
object BindingAdapter {
    @BindingAdapter("doing", "isFinished", "finished", requireAll = true)
    @JvmStatic
    fun finishOrNot(
        imageView: ImageView,
         doing: Drawable,
        isFinished: Boolean,
        finished: Drawable
    ) {
        if (isFinished) {
            imageView.setImageDrawable(finished)
        } else {
            imageView.setImageDrawable(finished)
        }
    }
}