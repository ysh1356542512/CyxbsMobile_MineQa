package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback.DiffItemCallback
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.holder.MultiTypeViewHolder

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : BaseAdapter类
 * @Request : God bless my code
 */
// 这里将LayoutManager向外扩展，方便操作RecyclerView滚动平移等操作
/*
    在这里对这个封装adapter进行说明
    外部通过notifyAdapterChanged传入基类Binder的List
    不管它的具体类型是什么 但每一个binder的hashCode是唯一的 所以把每个binder的hashCode传入成员变量mHashCodeViewType里去
    这样有什么用？ 可以辨识每个binder且可以储存所有binder
    覆写getItemViewType 用每个binder的hashCode来代替ViewType
    这样在onCreateViewHolder中的参数viewType就对应了hashcode 以其为key从哈希表中获得对应的binder
    而binder是我们自己定义的 其中binder的基类中有getLayoutId的方法 这样我们就能得到每个binder的ViewDataBinding
    从而实现在一个adapter中获得多个DataBinding
 */
class MultiTypeAdapter constructor(val layoutManager: RecyclerView.LayoutManager) :
        RecyclerView.Adapter<MultiTypeViewHolder>() {

    // 使用后台线程通过差异性计算来更新列表
    private val mAsyncListChange by lazy {
        AsyncListDiffer(
                this,
                DiffItemCallback<MultiTypeBinder<*>>()
        )
    }

    // 存储 MultiTypeBinder 和 MultiTypeViewHolder Type
    private var mHashCodeViewType = LinkedHashMap<Int, MultiTypeBinder<*>>()

    init {
        setHasStableIds(true)
    }

    fun notifyAdapterChanged(binders: List<MultiTypeBinder<*>>) {
        mHashCodeViewType = LinkedHashMap()
        binders.forEach {
            mHashCodeViewType[it.hashCode()] = it
        }
        mAsyncListChange.submitList(mHashCodeViewType.map { it.value })
    }

    fun notifyAdapterChanged(binder: MultiTypeBinder<*>) {
        mHashCodeViewType = LinkedHashMap()
        mHashCodeViewType[binder.hashCode()] = binder
        mAsyncListChange.submitList(mHashCodeViewType.map { it.value })
    }

    override fun getItemViewType(position: Int): Int {
        val mItemBinder = mAsyncListChange.currentList[position]
        val mHasCode = mItemBinder.hashCode()
        // 如果Map中不存在当前Binder的hasCode，则向Map中添加当前类型的Binder
        if (!mHashCodeViewType.containsKey(mHasCode)) {
            mHashCodeViewType[mHasCode] = mItemBinder
        }
        return mHasCode
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = mAsyncListChange.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder {
        try {
            return MultiTypeViewHolder(parent.inflateDataBinding(mHashCodeViewType[viewType]?.layoutId()!!))
        } catch (e: Exception) {
            throw NullPointerException("不存在${mHashCodeViewType[viewType]}类型的ViewHolder!")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        val mCurrentBinder =
                mAsyncListChange.currentList[position] as MultiTypeBinder<ViewDataBinding>
        holder.itemView.tag = mCurrentBinder.layoutId()
        holder.onBindViewHolder(mCurrentBinder)
    }
}