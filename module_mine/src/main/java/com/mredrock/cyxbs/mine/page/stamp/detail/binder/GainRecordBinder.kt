package com.mredrock.cyxbs.mine.page.stamp.detail.binder

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineRecycleItemGainBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.model.GainItemData
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainItemInfo
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.GainItemInfo

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  16:13
 *@signature 我们不明前路，却已在路上
 */
class GainRecordBinder(val data1: GainItemInfo) : MultiTypeBinder<MineRecycleItemGainBinding>() {

    override fun onBindViewHolder(binding: MineRecycleItemGainBinding) {
        binding.apply {
            data = data1
        }
    }

    override fun layoutId(): Int = R.layout.mine_recycle_item_gain

    override fun areContentsTheSame(other: Any): Boolean =
            other is GainRecordBinder && other.data1 == data1
}