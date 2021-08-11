package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback

import androidx.recyclerview.widget.DiffUtil
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : 用来处理差异性 判断两个item是否相等 和Rv另一种适配器 ListAdapter的一样也是用的这个
 * @Request : God bless my code
 */
class DiffItemCallback<T : MultiTypeBinder<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.layoutId() == newItem.layoutId()
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.hashCode() == newItem.hashCode() && oldItem.areContentsTheSame(newItem)
}