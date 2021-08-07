package com.mredrock.cyxbs.mine.page.stamp.center.binder

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineTaskRecycleItemMultiProgressBinding
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  21:00
 *@signature 我们不明前路，却已在路上
 */
class MultiTaskBinder(val moreTask: MoreTask) :
    MultiTypeBinder<MineTaskRecycleItemMultiProgressBinding>() {
    override fun layoutId(): Int = R.layout.mine_task_recycle_item_multi_progress

    override fun areContentsTheSame(other: Any): Boolean = false

    override fun onBindViewHolder(binding: MineTaskRecycleItemMultiProgressBinding) {
        binding?.data = moreTask
    }
}