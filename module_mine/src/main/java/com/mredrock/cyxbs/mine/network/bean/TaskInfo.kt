package com.mredrock.cyxbs.mine.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TaskInfo(
        @SerializedName("current_progress")
        val currentProgress: Int,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("max_progress")
        val maxProgress: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String
) :Serializable