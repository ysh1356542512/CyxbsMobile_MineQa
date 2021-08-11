package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : BaseViewHolder类 实现绑定Binding
 * @Request : God bless my code
 */
class MultiTypeViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), AutoCloseable {
    private var mAlreadyBinding: MultiTypeBinder<ViewDataBinding>? = null


    //绑定binding
    fun onBindViewHolder(items: MultiTypeBinder<ViewDataBinding>) {
        // 如果两次绑定的 Binding 不一致，则直接销毁
        if (mAlreadyBinding != null && items !== mAlreadyBinding) close()
        // 开始绑定
        items.bindViewDataBinding(binding)
        // 保存绑定的 Binding
        mAlreadyBinding = items
    }

    //销毁绑定的binding
    override fun close() {
        mAlreadyBinding?.unbindDataBinding()
        mAlreadyBinding = null
    }

}