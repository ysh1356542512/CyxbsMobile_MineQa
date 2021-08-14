package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeItemInfo

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
interface ExchangeDetailContract {
    interface IPresenter {
        fun getContent(): ExchangeDetailData?
//        //得到兑换详情
//        fun getExchangeData(func: (ExchangeInfo) -> Unit)
//        //得到明细详情
//        fun getGainData(func: (GainInfo) -> Unit)
        fun fetch(exchangeItemInfo: ExchangeItemInfo)
    }

    interface IVM {

        fun setExchangeDetail(value: ExchangeDetailData)
    }
}