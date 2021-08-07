package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class OrderResponse(
        @SerializedName("buy_name")
        val buyTime: String,
        @SerializedName("buyer")
        val buyer: Int,
        @SerializedName("num")
        val num: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("remain_time")
        val remainTime: Int,
        @SerializedName("status")
        val status: Boolean,
        @SerializedName("title")
        val title: String
):Serializable

