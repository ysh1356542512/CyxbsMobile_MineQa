package com.mredrock.cyxbs.mine.network.bean

class TaskInfo(
        val code: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
) {
    data class Data(
            val current_progress: Int,
            val description: String,
            val id: Int,
            val max_progress: Int,
            val title: String,
            val type: String
    )
}