package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.TaskContract
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  20:18
 *@signature 我们不明前路，却已在路上
 */
class StampTaskViewModel : BaseViewModel(),TaskContract.TaskIVM {
    //更多
    private val _moreTask:MutableLiveData<List<MoreTask>> = MutableLiveData()
    val task:LiveData<List<MoreTask>> = _moreTask

    private val _firsLevelTask:MutableLiveData<List<FirstLevelTask>> = MutableLiveData()
    val firstLevelTask:LiveData<List<FirstLevelTask>> = _firsLevelTask

    //private val
}