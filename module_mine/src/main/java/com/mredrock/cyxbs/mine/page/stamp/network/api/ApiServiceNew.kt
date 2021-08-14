package com.mredrock.cyxbs.mine.page.stamp.network.api


import com.mredrock.cyxbs.mine.page.stamp.network.bean.GoodsBuyRep
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ceter.CenterInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeInfo
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @Date : 2021/8/13   13:11
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
interface ApiServiceNew {

    @GET("magipoke-intergral/User/info")
    fun getCenterInfo(): Observable<CenterInfo>


    @GET("magipoke-intergral/User/exchange")
    fun getExchangeInfo(): Observable<ExchangeInfo>

    @GET("magipoke-intergral/User/getRecord")
    fun getGainInfo(@Query("page") page: Int, @Query("size") size: Int): Observable<GainInfo>

    @GET("magipoke-intergral/Integral/getItemInfo")
    fun getGoodsInfo(@Query("id") goodsId: String): Observable<GoodsInfo>

    @GET("")
    fun getExchangedDetail(userId: Int, exchangeId: Int): Observable<ExchangeInfo>


    @POST
    fun updateProgress(title: String)

    @FormUrlEncoded
    @POST("magipoke-intergral/Integral/order")
    fun postOrderInfo()

    @FormUrlEncoded
    @POST("magipoke-intergral/Integral/purchase")
    fun buyGoodsRep(@Field("id")userId: String): Observable<GoodsBuyRep>

}