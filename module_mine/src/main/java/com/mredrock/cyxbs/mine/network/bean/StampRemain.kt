package com.mredrock.cyxbs.mine.network.bean

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class StampRemain(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val msg: String,
            val remain: Int
    )
}

