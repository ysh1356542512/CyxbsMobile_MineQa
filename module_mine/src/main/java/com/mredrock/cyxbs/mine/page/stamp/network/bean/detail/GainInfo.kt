package com.mredrock.cyxbs.mine.page.stamp.network.bean.detail
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * @Date : 2021/8/13   12:55
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class GainInfo(
    @SerializedName("data")
    val `data`: List<GainData>,
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Int
):Serializable

data class GainData(
    @SerializedName("gain_stamp")
    val gainStamp: Int,
    @SerializedName("red_id")
    val redId: String,
    @SerializedName("task_name")
    val taskName: String
):Serializable

