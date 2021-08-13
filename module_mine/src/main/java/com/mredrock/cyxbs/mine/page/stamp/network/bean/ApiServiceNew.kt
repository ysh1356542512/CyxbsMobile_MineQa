package com.mredrock.cyxbs.mine.page.stamp.network.bean

import com.mredrock.cyxbs.common.bean.RedrockApiStatus
import io.reactivex.Observable
import com.mredrock.cyxbs.common.bean.RedrockApiWrapper
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
    fun getCenterInfo():Observable<RedrockApiWrapper<CenterInfo>>


    @GET("")
    fun getExchangeInfo(userId:Int):Observable<RedrockApiWrapper<ExchangeInfo>>

    @GET("")
    fun getGainInfo(userId: Int):Observable<RedrockApiWrapper<GainInfo>>

    @GET("")
    fun getGoodsInfo(@Query("goodsId")goodsId: Int):Observable<RedrockApiWrapper<GoodsInfo>>

    @GET("")
    fun getExchangedDetail(userId: Int,exchangeId:Int):Observable<RedrockApiWrapper<ExchangeDetailInfo>>

    fun updateProgress(title: String)

    fun postOrderInfo(userId: Int, goodsId: Int)


    @POST("")
    fun buyGoodsRep(goodsId: Int):Observable<GoodsBuyRep>

}