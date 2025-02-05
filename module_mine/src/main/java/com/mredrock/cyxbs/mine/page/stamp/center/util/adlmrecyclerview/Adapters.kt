package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : 扩展类
 * @Request : God bless my code
 */
fun createMultiTypeAdapter(
        recyclerView: RecyclerView,
        layoutManager: RecyclerView.LayoutManager
): MultiTypeAdapter {
    recyclerView.layoutManager = layoutManager
    val mMultiTypeAdapter = MultiTypeAdapter(layoutManager)
    recyclerView.adapter = mMultiTypeAdapter
    // 处理RecyclerView的触发回调
    recyclerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            mMultiTypeAdapter.onDetachedFromRecyclerView(recyclerView)
        }

        override fun onViewAttachedToWindow(v: View?) {}
    })
    return mMultiTypeAdapter
}

//MultiTypeGeneralAdapter扩展函数，重载MultiTypeGeneralAdapter类，使用invoke操作符调用MultiTypeGeneralAdapter内部函数。
inline operator fun MultiTypeAdapter.invoke(block: MultiTypeAdapter.() -> Unit): MultiTypeAdapter {
    this.block()
    return this
}


fun <T : ViewDataBinding> ViewGroup.inflateDataBinding(layoutId: Int): T =
        DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, false)