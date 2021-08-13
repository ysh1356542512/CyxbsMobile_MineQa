package com.mredrock.cyxbs.mine.page.stamp.detail.binder

import android.view.View
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineRecycleItemDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.ExchangeRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ExchangeItemInfo

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  15:03
 *@signature 我们不明前路，却已在路上
 */
class ExchangeRecordBinder(
        val data: ExchangeItemInfo,
        val handler: ExchangeRecordFragment.ClickHandler
) : MultiTypeBinder<MineRecycleItemDetailBinding>() {

    override fun onBindViewHolder(binding: MineRecycleItemDetailBinding) {
        binding.apply {
            data = data
            if (data!!.getOrNot) ivGet.visibility = View.GONE
        }
        binding.handler = handler
    }

    override fun layoutId(): Int = R.layout.mine_recycle_item_detail

    override fun areContentsTheSame(other: Any): Boolean =
            other is ExchangeRecordBinder && other.data == data

}