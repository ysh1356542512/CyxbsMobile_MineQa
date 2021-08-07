package com.mredrock.cyxbs.mine.network.bean

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class TaskComplete(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val stamp_num: Int
    )
}

