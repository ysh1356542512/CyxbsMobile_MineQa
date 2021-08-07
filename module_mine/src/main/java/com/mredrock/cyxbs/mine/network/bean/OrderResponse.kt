package com.mredrock.cyxbs.mine.network.bean

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class OrderResponse(
        val code: Int,
        val `data`: List<Data>,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val buy_time: String,
            val buyer: Int,
            val num: Int,
            val price: Int,
            val remain_time: Int,
            val status: Boolean,
            val title: String
    )
}

