package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class GoodsInfo(
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
        val urls: List<String>,
        @SerializedName("user_account")
        val userAccount: Int
): Serializable
