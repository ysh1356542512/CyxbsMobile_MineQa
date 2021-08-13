package com.mredrock.cyxbs.mine.page.stamp.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * @Date : 2021/8/13   12:52
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
data class CenterInfo(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("info")
        val info: String,
        @SerializedName("status")
        val status: Int
) : Serializable {
    data class Data(
            @SerializedName("shop")
            val shop: List<Shop>,
            @SerializedName("task")
            val task: List<Task>,
            @SerializedName("un_got_good")
            val unGotGood: Boolean,
            @SerializedName("user_amount")
            val userAmount: Int
    ) : Serializable{
        data class Shop(
                @SerializedName("amount")
                val amount: Int,
                @SerializedName("price")
                val price: Int,
                @SerializedName("title")
                val title: String,
                @SerializedName("type")
                val type: Int,
                @SerializedName("url")
                val url: String
        ): Serializable

        data class Task(
                @SerializedName("current_progress")
                val currentProgress: Int,
                @SerializedName("description")
                val description: String,
                @SerializedName("max_progress")
                val maxProgress: Int,
                @SerializedName("title")
                val title: String,
                @SerializedName("type")
                val type: String
        ): Serializable
    }
}





