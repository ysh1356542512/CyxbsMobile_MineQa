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
        @SerializedName("is_enter")
        val isEnter: Boolean,
        @SerializedName("user_account")
        val userAccount: Int
) :Serializable



