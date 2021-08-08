package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.TaskContract
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.MultiTypeAdapter

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:09
 *@signature 我们不明前路，却已在路上
 */
class TaskPresenter : BasePresenter<StampTaskViewModel>(),TaskContract.TaskIPresenter {
    override fun fetch() {

    }

    override fun setAdapter(mAdapter: MultiTypeAdapter?) {
        val x = vm
        vm?.firstLevelTask
    }

}