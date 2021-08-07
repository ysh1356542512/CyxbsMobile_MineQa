package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback

import android.view.View

/**
 * @Date : 2021/8/5
 * @By : ysh
 * @Usage : 可传参的onClickListener
 * @Request : God bless my code
 */
interface OnViewClickListener {

    //若无参数 传null
    fun onClick(view: View) {
        onClick(view, null)
    }

    fun onClick(view: View, any: Any?) {

    }

    fun onLongClick(view: View) {
        onLongClick(view, null)
    }

    fun onLongClick(view: View, any: Any?) {

    }
}