package com.mredrock.cyxbs.mine.network.bean

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class GoodsInfo(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val amount: Int,
            val description: String,
            val life: Int,
            val price: Int,
            val title: String,
            val type: Int,
            val urls: List<Url>,
            val user_account: Int
    ) {

        data class Url(
                val url: String
        )

    }

}

