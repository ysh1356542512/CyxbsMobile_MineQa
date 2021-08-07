package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class StampRemain(
        @SerializedName("msg")
        val msg: String,
        @SerializedName("remain")
        val remain: Int
):Serializable

