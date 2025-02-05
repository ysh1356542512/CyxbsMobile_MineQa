package com.mredrock.cyxbs.mine.page.stamp.exchange.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Date : 2021/8/4
 * @By : ysh
 * @Usage : 轮播图ViewPager的adapter
 * @Request : God bless my code
 */
abstract class BaseBannerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    protected var mList: MutableList<T> = mutableListOf()
    var isCanLoop = false
    var pageClickListener: OnPageClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate =
                LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        return createViewHolder(parent, inflate, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val realPosition: Int = getRealPosition(position)
        holder.itemView.setOnClickListener {
            pageClickListener?.onPageClick(realPosition, it)
        }
        onBind(holder, mList[realPosition], realPosition, mList.size)
    }

    override fun getItemCount(): Int {
        return if (isCanLoop && mList.size > 1) {
            Int.MAX_VALUE
        } else {
            mList.size
        }
    }

    fun getData(): List<T> {
        return mList
    }

    fun setData(list: List<T>) {
        mList.clear()
        mList.addAll(list)
    }

    fun getListSize(): Int {
        return mList.size
    }

    fun getRealPosition(position: Int): Int {
        val pageSize = mList.size
        if (pageSize == 0) {
            return 0
        }
        return if (isCanLoop) (position + pageSize) % pageSize else position
    }

    interface OnPageClickListener {
        fun onPageClick(position: Int, v: View)
    }


    protected abstract fun onBind(holder: VH, data: T, position: Int, pageSize: Int)
    abstract fun createViewHolder(parent: ViewGroup, itemView: View, viewType: Int): VH
    abstract fun getLayoutId(viewType: Int): Int

}