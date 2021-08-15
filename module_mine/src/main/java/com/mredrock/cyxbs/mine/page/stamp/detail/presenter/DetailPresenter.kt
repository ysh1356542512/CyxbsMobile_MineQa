package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

//import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo
import android.util.Log
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.ext.addFirstOrLast
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeItemInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainItemInfo

/**
 * @Date : 2021/8/13
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class DetailPresenter : BasePresenter<StampDetailViewModel>(),
    StampDetailActivityContract.IPresenter {


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

    fun getDefaultData() {
        vm?.apply {
            getDefaultExchangeData {
                setExchangeListDataValue(it)
            }
            getDefaultGainData {
                setGainListDataValue(it)
            }
        }
    }

    //获取“获取记录”界面的数据
    private fun getDefaultGainData(func: (GainInfo) -> Unit) {
        func(
            GainInfo(
                listOf(
                    GainItemInfo(40, 1617339294, "游览任务"),
                ),
                "Loading",
                -1
            )

        )
    }

    //获取”兑换记录“的数据
    private fun getDefaultExchangeData(func: (ExchangeInfo) -> Unit) {
        func(
            ExchangeInfo(
                listOf(
                    ExchangeItemInfo(1617339294, 245769, 4300, true, "卷卷鼠标垫"),
                    ExchangeItemInfo(1617339294, 245769, 4300, false, "卷卷鼠标垫"),
                    ExchangeItemInfo(1617339294, 245769, 4300, true, "卷卷鼠标垫")
                ),
                "Loading",
                -1
            )
        )

    }

    override fun getExchangeData(func: (ExchangeInfo) -> Unit) {
        apiServiceNew.getExchangeInfo()
            .setSchedulers()
            .doOnSubscribe { }
            .doOnError { }
            .safeSubscribeBy(
                onNext = {
                    Log.d("sss", "getExchangeData: ${it.status}+${it.info}")
                    val list: MutableList<ExchangeItemInfo> = mutableListOf()
                    it.data.forEach { it2 ->
                        list.addFirstOrLast(!it2.getOrNot, it2)
                    }
                    val new = ExchangeInfo(list, it.info, it.status)
                    func(new)
                },
                onError = {
                    BaseApp.context.toast("网络请求失败了呢~")
                },
                onComplete = {})

    }

    override fun getGainData(func: (GainInfo) -> Unit) {
        apiServiceNew.getGainInfo(1, 10)
            .setSchedulers()
            .doOnSubscribe { }
            .doOnError { }
            .safeSubscribeBy {
                Log.d("sss", "getGainData: $it")
                func(it)
            }
//        ApiGenerator.getApiService(ApiServiceNew::class.java)
//                .getGainInfo(1)
////                .mapOrThrowApiException()
//                .setSchedulers()
//                .doOnSubscribe {  }
//                .doOnError {  }
//                .safeSubscribeBy {
//                    func(it)
//                }
    }
    //        //获取兑换记录页数据
//         exchangeData = getExchangeData()
//        //更新
//        vm?.setExchangeListDataValue(exchangeData)
    //获取获取记录页的数据
//        val gainData = getGainData()
//        //更新
//        vm?.setGainListDataValue(gainData)

    /*private fun getDExchangeData(): ExchangeListData {
        return
    }*/

}