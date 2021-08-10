package com.mredrock.cyxbs.mine.page.stamp.center.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopPageData
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData
import com.mredrock.cyxbs.mine.page.stamp.center.presenter.CenterContract


//class StampCenterViewModel(private val userId:Int)
class StampCenterViewModel() : BaseViewModel(), CenterContract.CenterVM {

    //用于记录今天是否已经点击小店
    var isClickToday = false

    //我们要得到的bean类
    private val _userAccount = MutableLiveData<Int>()

    //邮票小店的数据
    private val _shopPageData: MutableLiveData<ShopPageData> = MutableLiveData()
    val shopPageData: LiveData<ShopPageData> = _shopPageData

    fun setShopPageDataValue(value: ShopPageData) {
        _shopPageData.value = value
    }

    //邮票任务的数据
    private val _tasks:MutableLiveData<StampTaskData> = MutableLiveData()
    val tasks:LiveData<StampTaskData> = _tasks

    //暴露给Presenter的接口
    override fun setTasksValue(value: StampTaskData){
        _tasks.value = value
    }

    //获得用户余额
    fun getUserAmount() {

    }

    fun getIsClicked(): Boolean {
        return isClickToday
    }


    //暴露的接口
    override fun setIsClickToday(value: Boolean) {
        isClickToday = value
    }

    override fun getIsClickToday(): Boolean = isClickToday

}