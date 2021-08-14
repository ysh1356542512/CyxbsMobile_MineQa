package com.mredrock.cyxbs.mine.page.stamp.network.bean.detail
import com.google.gson.annotations.SerializedName
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.Data
import java.io.Serializable


/**
 * @Date : 2021/8/13   12:54
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class ExchangeInfo(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Int
)

data class Data(
    @SerializedName("date")
    val date: Int,
    @SerializedName("goods_name")
    val goodsName: String,
    @SerializedName("goods_price")
    val goodsPrice: Int,
    @SerializedName("is_received")
    val isReceived: Boolean,
    @SerializedName("order_id")
    val orderId: Int
)

