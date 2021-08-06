package com.mredrock.cyxbs.mine.page.stamp.center.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  22:26
 *@signature 我们不明前路，却已在路上
 */
data class MoreTask(
    val taskName: String,
    val taskDescription: String,
    val progress: Int,
    val isFinished: Boolean
) {


}