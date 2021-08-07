package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class AmountResponse(
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("msg")
        val msg: String
): Serializable

