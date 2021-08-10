package com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.ExchangeDetailContract
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  17:36
 *@signature 我们不明前路，却已在路上
 */
class ExchangeDetailViewModel : BaseViewModel(), ExchangeDetailContract.IVM {

    //界面的主体内容
    private val _content: MutableLiveData<ExchangeDetailData> = MutableLiveData()
    val content: LiveData<ExchangeDetailData> = _content

    override fun setContent(value: ExchangeDetailData) {
        _content.value = value
    }

}