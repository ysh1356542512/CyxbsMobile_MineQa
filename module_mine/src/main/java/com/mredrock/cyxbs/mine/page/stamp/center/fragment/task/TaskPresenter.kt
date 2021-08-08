package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import android.view.View
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.center.binder.MultiTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.OneTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.TitleBinder
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.MultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:09
 *@signature 我们不明前路，却已在路上
 */
class TaskPresenter : BasePresenter<StampTaskViewModel>(), TaskContract.TaskIPresenter {
    override fun fetch() {
        //获取Task1
        val task1 = getTask1()
        //获取Title
        val title = getTitle()
        //获取Task2
        val task2 = getTask2()
        //设置数据
        vm?.setTasksValue(
            StampTaskData(task1, title, task2)
        )
    }

    private fun getTask2(): List<MoreTask> {
        return listOf(
            MoreTask("逛逛邮问", "浏览5条动态 +15", 1, false),
            MoreTask("逛逛邮问", "浏览5条动态 +15", 2, false),
            MoreTask("逛逛邮问", "浏览5条动态 +15", 3, false),
            MoreTask("逛逛邮问", "浏览5条动态 +15", 4, false),
            MoreTask("逛逛邮问", "浏览5条动态 +15", 5, false)
        )
    }

    private fun getTitle(): String = "更多任务"


    private fun getTask1(): List<FirstLevelTask> {
        return listOf(
            FirstLevelTask("每日打卡1", "每日签到 +10", false),
            FirstLevelTask("每日打卡2", "每日签到 +10", false),
            FirstLevelTask("每日打卡3", "每日签到 +10", false)
        )
    }


    override fun onClicked(view: View, any: Any?) {

    }

}