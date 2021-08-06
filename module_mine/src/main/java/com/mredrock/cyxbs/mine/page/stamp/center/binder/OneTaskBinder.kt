package com.mredrock.cyxbs.mine.page.stamp.center.binder

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineTaskRecycleItemOnePregressBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  20:56
 *@signature 我们不明前路，却已在路上
 */
class OneTaskBinder() : MultiTypeBinder<MineTaskRecycleItemOnePregressBinding>() {
    override fun layoutId(): Int = R.layout.mine_task_recycle_item_one_pregress

    override fun areContentsTheSame(other: Any): Boolean = false
}