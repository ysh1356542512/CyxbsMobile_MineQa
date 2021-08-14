package com.mredrock.cyxbs.mine.page.stamp.network.bean.detail
import com.google.gson.annotations.SerializedName
import com.mredrock.cyxbs.mine.page.stamp.detail.model.GainItemData
import java.io.Serializable


/**
 * @Date : 2021/8/13   12:55
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class GainInfo(
        @SerializedName("data")
        val `data`: List<GainItemInfo>,
        @SerializedName("info")
        val info: String,
        @SerializedName("status")
        val status: Int
):Serializable

data class GainItemInfo(
        @SerializedName("task_income")
        val price: Int,
        @SerializedName("date")
        val date: Long,
        @SerializedName("task_name")
        val name: String
):Serializable

