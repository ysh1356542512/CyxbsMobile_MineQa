package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.mapOrThrowApiException
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ApiServiceNew

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
class DetailPresenter : BasePresenter<StampDetailViewModel>(), StampDetailActivityContract.IPresenter {


    override fun fetch() {

        vm?.apply {
            getExchangeData {
                setExchangeListDataValue(it)
            }
            getGainData {
                setGainListDataValue(it)
            }
        }
    }

    override fun getExchangeData(func:(ExchangeInfo)->Unit){
        ApiGenerator.getApiService(ApiServiceNew::class.java)
                .getExchangeInfo(1)
                .mapOrThrowApiException()
                .setSchedulers()
                .doOnSubscribe {  }
                .doOnError {  }
                .safeSubscribeBy {
                    func(it)
                }
    }

    override fun getGainData(func: (GainInfo) -> Unit){
        ApiGenerator.getApiService(ApiServiceNew::class.java)
                .getGainInfo(1)
                .mapOrThrowApiException()
                .setSchedulers()
                .doOnSubscribe {  }
                .doOnError {  }
                .safeSubscribeBy {
                    func(it)
                }
    }
        //        //获取兑换记录页数据
//         exchangeData = getExchangeData()
//        //更新
//        vm?.setExchangeListDataValue(exchangeData)
        //获取获取记录页的数据
//        val gainData = getGainData()
//        //更新
//        vm?.setGainListDataValue(gainData)


//    private fun getGainData(): GainListData {
//        return GainListData(
//                listOf(
//                        GainItemData("游览任务", "2030-1-1", 40),
//                        GainItemData("游览任务", "2030-1-1", 40)
//                )
//        )
//    }

//    private fun getExchangeData(): ExchangeListData {
//        return ExchangeListData(
//                listOf(
//                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true),
//                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true),
//                        ExchangeItemData("卷卷鼠标垫", "2030-1-1", 4000, true)
//                )
//        )
//    }

}