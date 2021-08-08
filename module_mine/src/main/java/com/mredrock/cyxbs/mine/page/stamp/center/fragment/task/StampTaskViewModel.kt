package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  20:18
 *@signature 我们不明前路，却已在路上
 */
class StampTaskViewModel : BaseViewModel(), TaskContract.TaskIVM {
    private val _tasks:MutableLiveData<StampTaskData> = MutableLiveData()
    val tasks:LiveData<StampTaskData> = _tasks

    //暴露给Presenter的接口
    override fun setTasksValue(value: StampTaskData){
        _tasks.value = value
    }
}