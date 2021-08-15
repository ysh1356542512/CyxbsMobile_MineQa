package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeItemInfo

/**
 * @Date : 2021/8/13
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
interface ExchangeDetailContract {
    interface IPresenter {
        fun getContent(): ExchangeDetailData?
        fun fetch(exchangeItemInfo: ExchangeItemInfo)
    }

    interface IVM {
        fun setExchangeDetail(value: ExchangeDetailData)
    }
}