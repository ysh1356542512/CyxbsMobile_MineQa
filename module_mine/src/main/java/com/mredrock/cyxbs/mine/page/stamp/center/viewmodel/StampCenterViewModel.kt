package com.mredrock.cyxbs.mine.page.stamp.center.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.presenter.CenterContract


//class StampCenterViewModel(private val userId:Int)
class StampCenterViewModel() : BaseViewModel() ,CenterContract.CenterVM{

    //用于记录今天是否已经点击小店
    var isClickToday = false

    //我们要得到的bean类
    private val _userAccount = MutableLiveData<Int>()

    fun getIsClicked(): Boolean {
        return isClickToday
    }

    //暴露的信息
    override fun getIsTodayClicked(): Boolean = isClickToday
}