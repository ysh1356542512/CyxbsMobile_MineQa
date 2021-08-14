package com.mredrock.cyxbs.mine.page.stamp.network.bean
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.io.Serializable


/**
 * @Date : 2021/8/13   13:03
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class GoodsInfo(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Int
):Serializable{
    data class Data(
            @SerializedName("amount")
            val amount: Int,
            @SerializedName("description")
            val description: String,
            @SerializedName("life")
            val life: Int,
            @SerializedName("price")
            val price: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("urls")
            val urls: List<String>
    ):Serializable

}


