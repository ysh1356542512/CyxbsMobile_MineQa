package com.mredrock.cyxbs.mine.page.stamp.center.util

import android.annotation.SuppressLint
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Date : 2021/8/14   10:01
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
class TimestampUtils {

    //时间戳转日期
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun transToString(time: Long): String {
        val format = SimpleDateFormat("yyyy.MM.dd", Locale.CHINA)
        return format.format(time)
    }


    //日期转时间戳
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun transToTimeStamp(date: String): Long {
        return SimpleDateFormat("YY-MM-DD-hh-mm-ss").parse(date, ParsePosition(0)).time
    }
}