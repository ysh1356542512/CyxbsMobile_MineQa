package com.mredrock.cyxbs.mine.page.stamp.center.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.bean.CenterInfo

/**
 *@author ZhiQiang Tu
 *@time 2021/8/5  11:05
 *@signature 我们不明前路，却已在路上
 */
class StampCenterViewModel : BaseViewModel() {

    //用于记录今天是否已经点击小店
    private var isClickToday = false

    private val _isEnterToday = MutableLiveData<Boolean>()

    val isEnterToday:LiveData<Boolean>
    get() = _isEnterToday

    fun getIsEnterToday(){
//        apiService.getScoreStatus()
//                .mapOrThrowApiException()
//                .setSchedulers()
//                .doOnErrorWithDefaultErrorHandler { true }
//                .safeSubscribeBy(
//                        onNext = {
//                            _status.postValue(it)
//                        }
//                )
//                .lifeCycle()
    }


}