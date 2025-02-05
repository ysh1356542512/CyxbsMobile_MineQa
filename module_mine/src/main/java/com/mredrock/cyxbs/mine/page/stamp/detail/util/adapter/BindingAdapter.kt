package com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter

import android.graphics.Color
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

/**
 * 绑定任务界面根据任务是否完成而显示不同的按钮样式
 */
object BindingAdapter {
    @BindingAdapter("drawableFalse", "textFalse", "textState", "drawableTrue", "textTrue","textColorTrue","textColorFalse", requireAll = true)
    @JvmStatic
    fun statedTextView(
            btn: TextView?,
            drawableFalse: Drawable?,
            textFalse: String?,
            textState: Boolean?,
            drawableTrue: Drawable?,
            textTrue: String?,
            textColorTrue: Int,
            textColorFalse:Int, ) {
        textState ?: return
        if (textState) {
            btn?.text = textTrue
            btn?.background = drawableTrue
            btn?.setTextColor(textColorTrue)
        } else {
            btn?.text = textFalse
            btn?.background = drawableFalse
            btn?.setTextColor(textColorFalse)
        }
    }

    /**
     * 绑定图片
     */
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