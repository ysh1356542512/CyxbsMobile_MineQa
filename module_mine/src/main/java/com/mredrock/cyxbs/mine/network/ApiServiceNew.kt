package com.mredrock.cyxbs.mine.network

import android.database.Observable
import com.mredrock.cyxbs.common.bean.RedrockApiWrapper
import com.mredrock.cyxbs.mine.network.bean.*
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
interface ApiServiceNew {

    //获得主页面内容
    @GET
    fun getCenterInfo(userId: Int): Observable<RedrockApiWrapper<CenterInfo>>


    //获得邮票小店商品信息
    @GET
    fun getShopInfo(): Observable<RedrockApiWrapper<ShopInfo>>

    //任务信息
    @GET
    fun getTaskInfo(userId: Int): Observable<RedrockApiWrapper<TaskInfo>>

    //明细信息
    @GET
    fun getDetailResponse(userId: Int): Observable<RedrockApiWrapper<DetailResponse>>

    //订单信息
    @GET
    fun getOrderResponse(userId: Int): Observable<RedrockApiWrapper<OrderResponse>>

    //商品信息
    @GET
    fun getGoodsInfo(userId: Int, goodsId: Int): Observable<RedrockApiWrapper<GoodsInfo>>

    @POST
    fun postTaskComplete(userId: Int, taskId: Int): Observable<RedrockApiWrapper<TaskComplete>>

    @POST
    fun getAmountResponse(goodsId: Int): Observable<RedrockApiWrapper<AmountResponse>>

    @POST
    fun getStampRemain(userId: Int): Observable<RedrockApiWrapper<StampRemain>>
}