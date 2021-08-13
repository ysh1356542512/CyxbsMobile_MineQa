package com.mredrock.cyxbs.mine.page.stamp.network.bean

import android.database.Observable
import com.mredrock.cyxbs.common.bean.RedrockApiWrapper
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Date : 2021/8/13   13:11
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
interface ApiServiceNew {

    @GET("magipoke-intergral/User/info")
    fun getCenterInfo()


    fun getExchangeInfo(userId:Int)

    fun getGainInfo(userId: Int)

    @GET("")
    fun getGoodsInfo(@Query("goodsId")goodsId: Int):Observable<RedrockApiWrapper<GoodsInfo>>

    fun getExchangedDetail(userId: Int,exchangeId:Int)

    fun updateProgress(title: String)

    fun postOrderInfo(userId: Int, goodsId: Int)


}