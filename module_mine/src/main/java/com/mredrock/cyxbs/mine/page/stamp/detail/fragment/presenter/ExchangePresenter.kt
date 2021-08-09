package com.mredrock.cyxbs.mine.page.stamp.detail.fragment.presenter

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.presenter.IPresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeListData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  23:09
 *@signature 我们不明前路，却已在路上
 */
class ExchangePresenter : BasePresenter<StampDetailViewModel>() {
    override fun fetch() {

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