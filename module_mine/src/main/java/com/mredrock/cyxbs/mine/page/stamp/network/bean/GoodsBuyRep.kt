package com.mredrock.cyxbs.mine.page.stamp.network.bean
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * @Date : 2021/8/13   17:08
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class GoodsBuyRep(
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Int
):Serializable