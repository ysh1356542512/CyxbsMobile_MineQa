//module_mine/src/main/java/com/mredrock/cyxbs/mine/page/stamp/network/bean/ApiServiceNew.kt
package com.mredrock.cyxbs.mine.page.stamp.network.bean


import io.reactivex.Observable
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ceter.CenterInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeDetailInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeInfo
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Date : 2021/8/13   13:11
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
interface ApiServiceNew {

    @GET("magipoke-intergral/User/info")
    fun getCenterInfo():Observable<CenterInfo>


    @GET("magipoke-intergral/User/exchange")
    fun getExchangeInfo():Observable<ExchangeInfo>

    @GET("magipoke-intergral/User/getRecord")
    fun getGainInfo():Observable<GainInfo>

    @GET("magipoke-intergral/Integral/getItemInfo")
    fun getGoodsInfo(@Query("id")goodsId: String):Observable<GoodsInfo>

    @GET("")
    fun getExchangedDetail(userId: Int,exchangeId:Int):Observable<ExchangeDetailInfo>

    fun updateProgress(title: String)

    fun postOrderInfo(userId: Int, goodsId: Int)


    @POST("")
    fun buyGoodsRep(goodsId: Int):Observable<GoodsBuyRep>

}