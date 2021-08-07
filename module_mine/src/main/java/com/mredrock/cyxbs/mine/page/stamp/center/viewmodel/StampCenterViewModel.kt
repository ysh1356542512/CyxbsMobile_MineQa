package com.mredrock.cyxbs.mine.page.stamp.center.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel


//class StampCenterViewModel(private val userId:Int)
class StampCenterViewModel():BaseViewModel() {

    //用于记录今天是否已经点击小店
    var isClickToday = false
    //我们要得到的bean类
    private val _userAccount = MutableLiveData<Int>()




}