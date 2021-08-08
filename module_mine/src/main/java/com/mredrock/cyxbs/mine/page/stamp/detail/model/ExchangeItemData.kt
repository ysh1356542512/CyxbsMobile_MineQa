package com.mredrock.cyxbs.mine.page.stamp.detail.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  13:56
 *@signature 我们不明前路，却已在路上
 */
data class ExchangeListData(
    val data: List<ExchangeItemData>
)

data class ExchangeItemData(
    val name: String,
    val date: String,
    val price: Int,
    val getOrNot: Boolean
)