package com.mredrock.cyxbs.mine.page.stamp.center.util

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : 自定义CollapsibleToolbar 让该控件motion化 使其可做动画
 * @Request : God bless my code
 */
/*
    写下注释吧
    这个类就是给CollapsibleToolbar赋予可MotionLayout的能力 不知这个类 包括其他的viewGroup类型都可这样去转化
    继承自MotionLayout以及AppBarLayout的OnOffsetChangedListener接口
    将motionLayout的progress也就是动画的进度 和 AppBarLayout 的offset 也就是折叠动画的程度相关联
    也就是当AppBarLayout被折叠时 MotionLayout设定的动画也会与其同进度 实现主页面的动画效果
 */
class CollapsibleToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
//        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
        appBarLayout?.let {
            progress = -verticalOffset / it.totalScrollRange.toFloat()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}