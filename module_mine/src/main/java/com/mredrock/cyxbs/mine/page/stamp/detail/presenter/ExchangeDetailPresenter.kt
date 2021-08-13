package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ExchangeItemInfo

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
class ExchangeDetailPresenter(private val exchangeItemInfo: ExchangeItemInfo) : BasePresenter<ExchangeDetailViewModel>(), ExchangeDetailContract.IPresenter {

    override fun fetch() {
        val content = getContent()
        vm?.setExchangeDetail(content)
    }

    //由于时间原因 所以这里就不修改ExchangeDetailViewModel中的data类型 直接在P层转化为需要的
    override fun getContent():ExchangeDetailData{
        return if(exchangeItemInfo.getOrNot){
            ExchangeDetailData(100L,"已领取",exchangeItemInfo.name,exchangeItemInfo.price,exchangeItemInfo.date)
        }else{
            ExchangeDetailData(100L,"未领取",exchangeItemInfo.name,exchangeItemInfo.price,exchangeItemInfo.date)
        }
    }

//    private fun getContent():  {
//        return ExchangeDetailData(
//                System.currentTimeMillis(), "待领取", "卷卷鼠标垫", 1000, "2021-2-25 14:32"
//        )
//    }
}