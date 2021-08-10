package com.mredrock.cyxbs.mine.page.stamp.center.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  21:34
 *@signature 我们不明前路，却已在路上
 */
data class StampTaskData(
    val task1: List<FirstLevelTask>,
    val title: String,
    val task2: List<MoreTask>
)

//基础任务
data class FirstLevelTask(
    val taskName: String,
    val taskDescription: String,
    val isFinished: Boolean
)

//更多的任务
data class MoreTask(
    val taskName: String,
    val taskDescription: String,
    val progress: Int,
    val isFinished: Boolean
)
