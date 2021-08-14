package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeItemInfo
import com.mredrock.cyxbs.mine.page.stamp.utils.Tools

/**
 * @Date : 2021/8/13
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class ExchangeDetailPresenter() :
    BasePresenter<ExchangeDetailViewModel>(), ExchangeDetailContract.IPresenter {

    lateinit var exchangeItemInfo: ExchangeItemInfo

    override fun fetch() {
    }

    //由于时间原因 所以这里就不修改ExchangeDetailViewModel中的data类型 直接在P层转化为需要的
    override fun getContent(): ExchangeDetailData {
        return if (exchangeItemInfo.getOrNot) {
            ExchangeDetailData(exchangeItemInfo.id.toLong(),
                "已领取",
                exchangeItemInfo.name,
                exchangeItemInfo.price,
                Tools.convertLongToDate(exchangeItemInfo.date, "yyyy-MM-dd HH:mm"))
        } else {
            ExchangeDetailData(exchangeItemInfo.id.toLong(),
                "待领取",
                exchangeItemInfo.name,
                exchangeItemInfo.price,
                Tools.convertLongToDate(exchangeItemInfo.date, "yyyy-MM-dd HH:mm"))
        }
    }

    override fun fetch(exchangeItemInfo: ExchangeItemInfo) {
        this.exchangeItemInfo = exchangeItemInfo
        val content = getContent()
        vm?.setExchangeDetail(content)
    }

//    private fun getContent():  {
//        return ExchangeDetailData(
//                System.currentTimeMillis(), "待领取", "卷卷鼠标垫", 1000, "2021-2-25 14:32"
//        )
//    }
}