package com.mredrock.cyxbs.mine.page.stamp.network.bean.detail
import com.google.gson.annotations.SerializedName
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.Data
import java.io.Serializable


/**
 * @Date : 2021/8/13   12:54
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class ExchangeDetailInfo(
    @SerializedName("data")
    val `data`: List<Exchange>,
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Int
): Serializable

data class Exchange(
    @SerializedName("cos_stamp")
    val cosStamp: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("is_collected")
    val isCollected: Boolean,
    @SerializedName("moment")
    val moment: String,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("ware_name")
    val wareName: String
): Serializable

