package com.mredrock.cyxbs.mine.page.stamp.center.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  21:34
 *@signature 我们不明前路，却已在路上
 */
data class StampTaskData(
    val task1: MutableList<FirstLevelTask>,
    var title: String,
    val task2: MutableList<MoreTask>
)

//基础任务
data class FirstLevelTask(
    var taskName: String,
    var taskDescription: String,
    var progress: Int,
    var max: Int
)

//更多的任务
data class MoreTask(
    var taskName: String,
    var taskDescription: String,
    var progress: Int,
    var max: Int
)
