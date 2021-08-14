package com.mredrock.cyxbs.mine.page.stamp.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 *@author ZhiQiang Tu
 *@time 2021/8/14  16:17
 *@signature 我们不明前路，却已在路上
 */
object Tools {
    fun convertLongToDate(long: Long,formatStr: String):String{
        val date = Date(long)
        val format = SimpleDateFormat(formatStr, Locale.CHINA)
        return format.format(date)
    }
}