package com.mredrock.cyxbs.mine.page.stamp.network.bean
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
    val `data`: List<ExchangeItemInfo>,
        @SerializedName("info")
    val info: String,
        @SerializedName("status")
    val status: Int
):Serializable
data class ExchangeItemInfo(
        @SerializedName("buy_time")
        val date: String,
        @SerializedName("num")
        val num: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("RemainTime")
        val remainTime: Int,
        @SerializedName("status")
        val getOrNot: Boolean,
        @SerializedName("title")
        val name: String
):Serializable

