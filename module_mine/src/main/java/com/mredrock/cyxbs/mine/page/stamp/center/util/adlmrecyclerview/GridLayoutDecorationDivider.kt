package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : 工具类
 * @Request : God bless my code
 */
class GridLayoutDecorationDivider(
        context: Context,
        private val spanCount: Int,
        dividerWidthDp: Int
) : RecyclerView.ItemDecoration() {
    private val dividerWidth: Int
    private val dividerWidthTop: Int
    private val dividerWidthBot: Int
    private val dividerPaint: Paint = Paint()
    override fun getItemOffsets(
            outRect: Rect,
            child: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, child, parent, state)
        val pos = parent.getChildAdapterPosition(child)
        val column = pos % spanCount // 计算这个child 处于第几列
        outRect.top = dividerWidthTop
        outRect.bottom = dividerWidthBot
        outRect.left = column * dividerWidth / spanCount
        outRect.right = dividerWidth - (column + 1) * dividerWidth / spanCount
    }

    private fun dpToPx(context: Context, value: Float): Int {
        return if (value <= 0) 0 else (value * context.resources.displayMetrics.density + 0.5f).toInt()
    }


    init {
        dividerPaint.color = Color.BLUE
        dividerWidth = dpToPx(context, dividerWidthDp.toFloat())
        dividerWidthTop = dividerWidth / 2
        dividerWidthBot = dividerWidth - dividerWidthTop
    }
}