package com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  22:57
 *@signature 我们不明前路，却已在路上
 */
object BindingAdapter {
    @BindingAdapter("drawableFalse",
        "textFalse",
        "textState",
        "drawableTrue",
        "textTrue",
        requireAll = true)
    @JvmStatic
    fun statedTextView(
        btn: TextView?,
        drawableFalse: Drawable?,
        textFalse: String?,
        textState: Boolean?,
        drawableTrue: Drawable?,
        textTrue: String?,
    ) {
        textState ?: return
        if (textState) {
            btn?.text = textTrue
            btn?.background = drawableTrue
        } else {
            btn?.text = textFalse
            btn?.background = drawableFalse
        }
    }

    @JvmStatic
    @BindingAdapter("imageState", "drawableFalse", "drawableTrue", requireAll = true)
    fun statedImageView(
        imageView: ImageView?,
        state: Boolean?,
        drawableFalse: Drawable?,
        drawableTrue: Drawable?,
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
        backgroundTrue: Drawable?,
    ) {
        if (state == null) return
        if (state) {
            view?.background = backgroundTrue
        } else {
            view?.background = backgroundFalse
        }
    }

    @JvmStatic
    @BindingAdapter("netImage", "placeholder", "error", requireAll = true)
    fun netImage(
        imageView: ImageView, url: String?,
        placeholder: Drawable?,
        error: Drawable?,
    ) {
        url ?: return
        Glide.with(imageView).load(url).placeholder(placeholder).error(error).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("showOrNot")
    fun showOrNot(view: View?, showOrNot: Boolean?) {
        if (showOrNot == false) {
            view?.visibility = View.GONE
        } else {
            view?.visibility = View.VISIBLE
        }
    }

}