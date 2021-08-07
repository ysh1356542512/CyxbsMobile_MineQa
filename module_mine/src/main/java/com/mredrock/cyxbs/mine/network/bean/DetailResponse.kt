package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class DetailResponse(
        @SerializedName("date")
        val date: String,
        @SerializedName("gain_stamp")
        val gainStamp: Int,
        @SerializedName("task_name")
        val taskName: String
): Serializable

