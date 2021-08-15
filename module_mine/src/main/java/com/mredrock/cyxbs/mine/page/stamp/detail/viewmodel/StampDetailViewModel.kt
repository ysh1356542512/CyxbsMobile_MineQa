package com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.detail.presenter.StampDetailActivityContract
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  11:39
 *@signature 我们不明前路，却已在路上
 */
class StampDetailViewModel : BaseViewModel(), StampDetailActivityContract.IVM {
    //兑换记录页面的rv数据
    private val _exchangeList: MutableLiveData<ExchangeInfo> = MutableLiveData()
    val exchangeListData: LiveData<ExchangeInfo> = _exchangeList

    fun setExchangeListDataValue(value: ExchangeInfo) {
        _exchangeList.value = value
    }

    //获取记录页的rv数据
    private val _gainList: MutableLiveData<GainInfo> = MutableLiveData()
    val gainListData: LiveData<GainInfo> = _gainList

    fun setGainListDataValue(value: GainInfo) {
        _gainList.value = value
    }

}