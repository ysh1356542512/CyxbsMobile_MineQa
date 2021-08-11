package com.mredrock.cyxbs.mine.page.stamp.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView

/**
 * @Date : 2021/8/9
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class MarqueeViewVertical @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val firstView = TextView(context)
    private val secondView = TextView(context)
    private lateinit var mTextArray: Array<String>
    private var currentTextIndex = 0

    init {
        firstView.setTextColor(Color.WHITE)
        firstView.setPadding(10, 0, 0, 0)
        secondView.setPadding(10, 0, 0, 0)
        secondView.setTextColor(Color.WHITE)
        firstView.alpha = 0.9f
        secondView.alpha = 0.9f
        firstView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        secondView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        firstView.typeface = Typeface.create("Bauhaus 93", Typeface.NORMAL)
        secondView.typeface = Typeface.create("font/bauhaus_93.ttf", Typeface.NORMAL)
        addView(firstView)
        addView(secondView)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //孩子的高度
        val childHeightSpec =
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST)
        firstView.measure(widthMeasureSpec, childHeightSpec)
        secondView.measure(widthMeasureSpec, childHeightSpec)

        //输出高度
        val firstHeight = firstView.measuredHeight
        val secondHeight = secondView.measuredHeight

        //自己的高度
        val maxHeight = firstHeight.coerceAtLeast(secondHeight)
        val selfHeightSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY)
        setMeasuredDimension(widthMeasureSpec, selfHeightSpec)

    }

    fun setTextArray(textArray: Array<String>) {
        if (textArray.isEmpty()) {
            throw IllegalAccessException("TextArray is empty")
        }
        this.mTextArray = textArray
        firstView.text = textArray[0]
        if (textArray.size > 1) {
            secondView.text = textArray[1]
            currentTextIndex = 1
        }

    }

    private fun doAnimator() {
        if (mCurrentDirection == RunDirection.BOTTOM_2_TOP) {
            val firstAnimator = ObjectAnimator.ofFloat(

                    firstView,
                    "translationY",
                    0f,
                    -firstView.measuredHeight.toFloat()
            )
            val secondAnimator = ObjectAnimator.ofFloat(
                    secondView,
                    "translationY",
                    0f,
                    -secondView.measuredHeight.toFloat()
            )
            val animatorSet = AnimatorSet()
            animatorSet.apply {
                duration = 500
                playTogether(firstAnimator, secondAnimator)
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        removeCallbacks(runTask)
                        postDelayed(runTask, 2000)
                    }
                })
                start()
            }
        } else {

        }
    }

    private val runTask = Runnable {
        //之前进行内容切换
        val firstText = mTextArray[currentTextIndex]
        firstView.text = firstText
        currentTextIndex++
        if (currentTextIndex >= mTextArray.size) {
            currentTextIndex = 0
        }
        val secondText = mTextArray[currentTextIndex]
        secondView.text = secondText
        doAnimator()
    }

    enum class RunDirection {
        BOTTOM_2_TOP, TOP_2_BOTTOM
    }

    private var mCurrentDirection = RunDirection.BOTTOM_2_TOP

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        firstView.layout(0, 0, firstView.measuredWidth, firstView.measuredHeight)
        if (mCurrentDirection == RunDirection.BOTTOM_2_TOP) {
            secondView.layout(
                    0, firstView.measuredHeight, secondView.measuredWidth,
                    firstView.measuredHeight + secondView.measuredHeight
            )
        } else {
            secondView.layout(0, -secondView.measuredHeight, secondView.measuredWidth, 0)
        }
        if (this.mTextArray.size > 1) {
            doAnimator()
        }
    }
}