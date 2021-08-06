package com.mredrock.cyxbs.mine.network.bean

class ShopInfo(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val amount: Any,
            val id: Int,
            val price: Int,
            val title: String,
            val type: Int,
            val url: String
    )
}