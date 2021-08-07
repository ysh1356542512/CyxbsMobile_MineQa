package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ShopInfo(
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("url")
        val url: String
) :Serializable