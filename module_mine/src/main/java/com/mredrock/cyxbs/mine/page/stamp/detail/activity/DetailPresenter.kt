package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeListData
import com.mredrock.cyxbs.mine.page.stamp.detail.model.GainItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.model.GainListData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  22:29
 *@signature 我们不明前路，却已在路上
 */
class DetailPresenter : BasePresenter<StampDetailViewModel>(), StampDetailActivityContract.IPresenter {
    override fun fetch() {
        //获取兑换记录页数据
        val exchangeData = getExchangeData()
        //更新
        vm?.setExchangeListDataValue(exchangeData)
        //获取获取记录页的数据
        val gainData = getGainData()
        //更新
        vm?.setGainListDataValue(gainData)
    }

    private fun getGainData(): GainListData {
        return GainListData(
                listOf(
                        GainItemData("游览任务", "2030-1-1", 40),
                        GainItemData("游览任务", "2030-1-1", 40)
                )
        )
    }

    private fun getExchangeData(): ExchangeListData {
        return ExchangeListData(
                listOf(
                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true),
                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true),
                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true)
                )
        )
    }

}