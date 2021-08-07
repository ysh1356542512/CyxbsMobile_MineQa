package com.mredrock.cyxbs.mine.network.bean

import okhttp3.internal.concurrent.Task
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
data class CenterInfo(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) :Serializable{
    data class Data(
            val isEnter: Boolean,
            val user_account: Int
    )

}



