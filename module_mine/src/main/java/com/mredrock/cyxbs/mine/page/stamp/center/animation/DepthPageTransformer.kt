package com.mredrock.cyxbs.mine.page.stamp.center.animation

import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

private const val MIN_SCALE = 0.75f

@RequiresApi(21)
class DepthPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> {
                    //当当前页在屏幕左侧 透明度完全为0
                    alpha = 0f
                }
                position <= 0 -> {
                    // 当用户向右滑 即当前页向左移动 不做任何变化
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    // 当用户向左滑 即当前页又有移动
                    //透明度根据位移变小
                    alpha = 1 - position

                    // 缩小宽度
                    translationX = pageWidth * -position
                    // 减小z轴高度 移动到左侧后面
                    translationZ = -1f

                    // 根据位移来计算缩小的大小 定义MINE_SCALE为0.75 使得缩小比例最小不能低于0.75
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    //当当前页在屏幕右侧 透明度为0
                    alpha = 0f
                }
            }
        }
    }
}