package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.mine.page.stamp.network.bean.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GainInfo

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
interface StampDetailActivityContract {

    interface IPresenter {
        //得到兑换详情
        fun getExchangeData(func: (ExchangeInfo) -> Unit)
        //得到明细详情
        fun getGainData(func: (GainInfo) -> Unit)
    }

    interface IVM {

    }
}