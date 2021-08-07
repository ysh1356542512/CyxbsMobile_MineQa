package com.mredrock.cyxbs.mine.network.bean

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class DetailResponse(
        val code: Int,
        val `data`: List<Data>,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val date: String,
            val gain_stamp: Int,
            val task_name: String
    )
}

