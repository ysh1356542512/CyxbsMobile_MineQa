package com.mredrock.cyxbs.mine.page.stamp.center.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  9:49
 *@signature 我们不明前路，却已在路上
 */
data class ExchangeDetail(
    val id: Long,
    val name: String,
    val price: Int,
    val date: String,
    val status: String
)