package com.mredrock.cyxbs.mine.page.stamp.center.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShopInfo(
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
) : Serializable