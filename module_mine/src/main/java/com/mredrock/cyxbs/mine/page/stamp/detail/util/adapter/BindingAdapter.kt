package com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  22:57
 *@signature 我们不明前路，却已在路上
 */
object BindingAdapter {
    @BindingAdapter("doing", "isFinished", "finished", requireAll = true)
    @JvmStatic
    fun finishOrNot(
        btn: Button?,
        doing: Drawable?,
        isFinished: Boolean?,
        finished: Drawable?
    ) {
        isFinished ?: return
        if (isFinished) {
            btn?.text = "已完成"
            btn?.background = finished
        } else {
            btn?.text = "去完成"
            btn?.background = doing
        }
    }

    @JvmStatic
    @BindingAdapter("imageState", "drawableFalse", "drawableTrue", requireAll = true)
    fun imageState(
        imageView: ImageView?,
        state: Boolean?,
        drawableFalse: Drawable?,
        drawableTrue: Drawable?
    ) {
        state ?: return
        if (state) {
            imageView?.setImageDrawable(drawableTrue)
        } else {
            imageView?.setImageDrawable(drawableFalse)
        }
    }

    @JvmStatic
    @BindingAdapter("viewState", "backgroundFalse", "backgroundTrue", requireAll = true)
    fun stateView(
        view: View?,
        state: Boolean?,
        backgroundFalse: Drawable?,
        backgroundTrue: Drawable?
    ) {
        if (state == null) return
        if (state) {
            view?.background = backgroundTrue
        } else {
            view?.background = backgroundFalse
        }
    }

    @JvmStatic
    @BindingAdapter("netImage")
    fun netImage(imageView: ImageView, url: String?) {
        url ?: return
        Glide.with(imageView).load(url).into(imageView)
    }

}