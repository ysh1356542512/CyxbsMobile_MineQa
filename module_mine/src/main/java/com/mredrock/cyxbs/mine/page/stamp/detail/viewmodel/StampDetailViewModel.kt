package com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.StampDetailActivityContract
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeListData

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  11:39
 *@signature 我们不明前路，却已在路上
 */
class StampDetailViewModel : BaseViewModel(),StampDetailActivityContract.IVM {
    private val _exchangeList:MutableLiveData<ExchangeListData> = MutableLiveData()
    val exchangeListData:LiveData<ExchangeListData> = _exchangeList

    fun setExchangeListDataValue(value:ExchangeListData){
        _exchangeList.value = value
    }

}