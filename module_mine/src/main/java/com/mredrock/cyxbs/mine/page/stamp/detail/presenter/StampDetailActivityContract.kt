package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

//import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeInfo
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeInfo

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
interface StampDetailActivityContract {

    interface IPresenter {
        //得到兑换详情
        //得到明细详情
        fun getGainData(func: (GainInfo) -> Unit)
        fun getExchangeData(func: (ExchangeInfo) -> Unit)
    }

    interface IVM {

    }
}