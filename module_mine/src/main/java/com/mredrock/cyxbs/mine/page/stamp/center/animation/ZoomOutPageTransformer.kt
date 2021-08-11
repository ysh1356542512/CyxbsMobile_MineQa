package com.mredrock.cyxbs.mine.page.stamp.center.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    //-1和1的情况一样
                    alpha = 0f
                }
                position <= 1 -> {
                    //缩放因素与position挂钩
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position) / 5)
                    //水平垂直margin与缩放因素挂钩
                    val vertMargin = pageHeight * (1 - scaleFactor) / 4
                    val horzMargin = pageWidth * (1 - scaleFactor) / 4

                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}