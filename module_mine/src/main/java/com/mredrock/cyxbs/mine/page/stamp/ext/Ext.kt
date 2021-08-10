package com.mredrock.cyxbs.mine.page.stamp.ext

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.mredrock.cyxbs.common.utils.extensions.editor
import com.mredrock.cyxbs.common.utils.extensions.sharedPreferences
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.MultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.util.extension.log
import java.text.SimpleDateFormat
import java.util.*

//SharedPreference
private var dateSp: SharedPreferences? = null

//SP数据库的名称
private const val SP_NAME: String = "DATE_SP_NAME"

//存入Date(时间)的key值
private const val DATE_KEY_NAME = "DATA_KEY_NAME"

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  8:20
 *@signature 我们不明前路，却已在路上
 */


//获取是否是第一次进入
fun Context.isFirstTimeComeIn(): Boolean {

    dateSp = sharedPreferences(SP_NAME)
    val date = dateSp?.getString(DATE_KEY_NAME, "NULL") ?: "NULL"
    //如果当前值没有传入
    return if (date == "NULL") {
        putDate()
        true
    } else {
        //如果是第一次进入
        if (compareDate(getCurrentDate(), date)) {
            log("当天第一次进入邮票任务")
            //存入当前时间
            putDate()
            true
        }
        //如果不是第一次进入
        else {
            log("你之前已经进入了")
            false
        }
    }
}

//放入当前的时间信息
private fun putDate() {
    dateSp?.editor {
        getCurrentDate().let {
            if (it != "NULL") {
                putString(DATE_KEY_NAME, it)
            }
        }
    }

}

//比对是当日的第一次进入
private fun compareDate(current: String, before: String): Boolean {
    val currentTimeList = current.split("-")
    val beforeTimeList = before.split("-")

    val beforeTime = beforeTimeList.map {
        it.toInt()
    }

    val currentTime = currentTimeList.map {
        it.toInt()
    }
    //如果当前时间的年月日有一项大于sp数据库的时间那么说明当前是最新的进入情况
    for (i in 0 until 2) {
        if (currentTimeList[i] > beforeTimeList[i]) {
            return true
        }
    }

    return false
}

//获取当前的时间信息
private fun getCurrentDate(): String {
    val timePills = System.currentTimeMillis()
    val date = Date(timePills)
    val format = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA)
    return format.format(date) ?: "NULL"
}



fun MultiTypeAdapter.setSpanCount(layoutManager: GridLayoutManager){
        layoutManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when(getItemViewType(position)){
                    else->log(getItemViewType(position).toString())
                }
                return 2
            }

        }
}
