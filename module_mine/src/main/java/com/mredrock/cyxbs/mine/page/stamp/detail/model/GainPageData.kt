package com.mredrock.cyxbs.mine.page.stamp.detail.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  10:43
 *@signature 我们不明前路，却已在路上
 */
data class GainListData(
        val data: List<GainItemData>
)

data class GainItemData(
        val name: String,
        val date: String,
        val price: Int
)