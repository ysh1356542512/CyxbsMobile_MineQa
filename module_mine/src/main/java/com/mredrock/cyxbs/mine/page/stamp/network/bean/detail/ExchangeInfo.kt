package com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * @Date : 2021/8/13   13:01
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class ExchangeInfo(
        @SerializedName("data")
        val data: List<ExchangeItemInfo>,
        @SerializedName("info")
        val info: String,
        @SerializedName("status")
        val status: Int
):Serializable
data class ExchangeItemInfo(
        @SerializedName("date")
        val date: Long,
        @SerializedName("order_id")
        val id: Int,
        @SerializedName("goods_price")
        val price: Int,
        @SerializedName("is_received")
        val getOrNot: Boolean,
        @SerializedName("goods_name")
        val name: String
):Serializable

