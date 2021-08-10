package com.mredrock.cyxbs.mine.page.stamp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
* @Date : 2021/8/9
* @By : ysh
* @Usage : 自定义ProgressBar 第一个自己画的的自定义View 看了一天教程没有白费hhh
* @Request : God bless my code
*/
class ProgressBar(context: Context?,attrs: AttributeSet?) : View(context,attrs) {
    private var currentProgress = 0f
    private var maxProgress = 0f

    /*
    **分为四个部分
    * 先绘制最左侧的半圆
    * 再绘制中间蓝色的矩形
    * 再绘制蓝色右边的灰色矩形
    * 再绘制灰色矩形右边的半圆
    * 最后再绘制蓝色矩形右边的半圆覆盖灰色矩形
     */
    //用dispatchDraw来多次刷新UI
    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.save()
        //得到半径
        val radius = height / 2f
        val rectWidth = (width - 2 * radius) * currentProgress / maxProgress
        //两只画笔 分别用来绘制蓝色和灰色矩形
        val mPaint1 = Paint()
        val mPaint2 = Paint()
        mPaint1.color = Color.parseColor("#7D8AFF")
        mPaint1.style = Paint.Style.FILL
        mPaint2.color = Color.parseColor("#E5E5E5")
        mPaint2.style = Paint.Style.FILL
        canvas?.apply {
            //最左侧半圆 虽然是个圆但后面会被覆盖
            drawCircle(radius, radius, radius, mPaint1)
            //蓝色矩形
            drawRect(radius, height * 1f, radius + rectWidth, 0f, mPaint1)
            //灰色矩形
            drawRect(radius + rectWidth, height * 1f, width - radius, 0f, mPaint2)
            //最右侧灰色半圆
            drawCircle(width - radius, height / 2f, radius, mPaint2)
            //蓝色矩形右侧半圆
            drawCircle(radius+rectWidth,height/2f,radius,mPaint1)
        }
        canvas?.restore()
        super.dispatchDraw(canvas)
    }

    //暴露给外部设置最大进度
    fun setMaxProgress(mProgress: Float){
        maxProgress = mProgress
    }

    //暴露给外部设置当前进度 每次调用这个方法都会 postInvalidate 来重新调用dispatchDraw方法
    fun setCurrentProgress(cProgress: Float){
        currentProgress = cProgress
        postInvalidate()
    }
}