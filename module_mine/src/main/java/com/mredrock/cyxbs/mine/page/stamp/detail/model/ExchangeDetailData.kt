package com.mredrock.cyxbs.mine.page.stamp.detail.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  11:09
 *@signature 我们不明前路，却已在路上
 */
data class ExchangeDetailData(
    val id: Long,
    val status: String,
    val name: String,
    val price: Int,
    val date: String
)