package com.mredrock.cyxbs.mine.page.stamp.center.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopPageData
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData


//class StampCenterViewModel(private val userId:Int)
class StampCenterViewModel() : BaseViewModel(), StampCenterContract.CenterVM {

    //用于记录今天是否已经点击小店
    var isClickedToday: Boolean = false

    //邮票小店的数据
    private val _shopPageData: MutableLiveData<ShopPageData> = MutableLiveData()
    val shopPageData: LiveData<ShopPageData> = _shopPageData

    //暴露给Presenter的接口
    override fun setShopPageDataValue(value: ShopPageData) {
        _shopPageData.value = value
    }

    //邮票任务的数据
    private val _tasks: MutableLiveData<StampTaskData> = MutableLiveData()
    val tasks: LiveData<StampTaskData> = _tasks

    //暴露给Presenter的接口
    override fun setTasksValue(value: StampTaskData) {
        _tasks.postValue(value)
    }

    //我们要得到的bean类
    private val _userAccount = MutableLiveData<Int>()
    val userAccount = _userAccount
    //获得用户余额
    fun setUserAccount(value:Int) {
        _userAccount.value = value
    }

    private val _hasGoodsToGet:MutableLiveData<Boolean> = MutableLiveData()
    val hasGoodsToGet = _hasGoodsToGet
    fun setHasGoodsToGet(value:Boolean){
        _hasGoodsToGet.value = value
    }
}