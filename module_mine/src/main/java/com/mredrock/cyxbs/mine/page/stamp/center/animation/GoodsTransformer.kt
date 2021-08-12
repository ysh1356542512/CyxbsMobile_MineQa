package com.mredrock.cyxbs.mine.page.stamp.center.animation

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * @Date : 2021/8/12   11:17
 * @By ysh
 * @Usage : 商品详情vp切换动画
 * @Request : God bless my code
 **/
class GoodsTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWith = width

            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    translationX = (-(1 - position) * 0.5 * pageWith).toFloat()

                }
                position <=1 ->{

                }
                position >1 ->{
                    alpha = 0f
                }
            }
        }
    }
}