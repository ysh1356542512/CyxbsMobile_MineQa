package com.mredrock.cyxbs.mine.page.stamp.center.binder

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineShopRecycleItemProductOneBinding
import com.mredrock.cyxbs.mine.databinding.MineShopRecycleItemProductTwoBinding
import com.mredrock.cyxbs.mine.databinding.MineShopRecyleItemTitleBinding
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductTwo
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopTitle
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  14:35
 *@signature 我们不明前路，却已在路上
 */

/**
 *  每个binder对应一种item类型 在onBindViewHolder进行具体的设定
 */
class GoodsTitleBinder(val title: ShopTitle) : MultiTypeBinder<MineShopRecyleItemTitleBinding>() {
    override fun layoutId(): Int = R.layout.mine_shop_recyle_item_title

    override fun areContentsTheSame(other: Any): Boolean =
            other is GoodsTitleBinder && other.title == title

    override fun onBindViewHolder(binding: MineShopRecyleItemTitleBinding) {
        binding.data = title
    }
}

class GoodsProductOneBinder(val data: ShopProductTwo) :
        MultiTypeBinder<MineShopRecycleItemProductOneBinding>() {
    override fun layoutId(): Int = R.layout.mine_shop_recycle_item_product_one

    override fun areContentsTheSame(other: Any): Boolean = other is GoodsProductOneBinder && other.data == data

    override fun onBindViewHolder(binding: MineShopRecycleItemProductOneBinding) {
        binding.data = data
        binding.eventHandler = this
    }
}

class GoodsProductTwoBinder(val data: ShopProductTwo) : MultiTypeBinder<MineShopRecycleItemProductTwoBinding>() {
    override fun layoutId(): Int = R.layout.mine_shop_recycle_item_product_two

    override fun areContentsTheSame(other: Any): Boolean =
            other is GoodsProductTwoBinder && other.data == data

    override fun onBindViewHolder(binding: MineShopRecycleItemProductTwoBinding) {
        binding.data = data
        binding.eventHandler = this
    }
}
