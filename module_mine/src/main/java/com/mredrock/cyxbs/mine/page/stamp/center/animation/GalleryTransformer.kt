package com.mredrock.cyxbs.mine.page.stamp.center.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * @Date : 2021/8/7
 * @By : ysh
 * @Usage : 切换动画
 * @Request : God bless my code
 */
private const val MIN_SCALE = 0.5f

class GalleryTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {

        val scaleValue = 1 - abs(position) * MIN_SCALE
        view.apply {
            scaleX = scaleValue
            scaleY = scaleValue
            alpha = scaleValue
            pivotX = view.width * (1 - position - (if (position > 0) 1 else -1) * 0.75f) * MIN_SCALE
            pivotY = view.height * (1 - scaleValue)
            elevation = if (position > -0.25 && position < 0.25) 1F else 0F
        }

    }
}