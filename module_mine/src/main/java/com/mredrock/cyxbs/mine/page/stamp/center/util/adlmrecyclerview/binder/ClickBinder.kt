package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder

import android.view.View
import com.mredrock.cyxbs.mine.BuildConfig
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback.OnViewClickListener

/**
 * @Date : 2021/8/5
 * @By : ysh
 * @Usage : 实现点击接口 实现点击监听
 * @Request : God bless my code
 */
open class ClickBinder : OnViewClickListener {

    protected open var mOnClickListener: ((view: View, any: Any?) -> Unit)? = null

    protected open var mOnLongClickListener: ((view: View, any: Any?) -> Unit)? = null

    //这是暴露给每一个item的方法 而传入的是一个函数类型的参数 也就是外部覆写的onClick方法
    open fun setOnClickListener(listener: (view: View, any: Any?) -> Unit): ClickBinder {
        this.mOnClickListener = listener
        return this
    }

    open fun setOnLongClickListener(listener: (view: View, any: Any?) -> Unit): ClickBinder {
        this.mOnLongClickListener = listener
        return this
    }

    override fun onClick(view: View) {
        onClick(view, null)
    }

    override fun onClick(view: View, any: Any?) {
        if (mOnClickListener != null) {
            //在这里调用设置的方法
            mOnClickListener?.invoke(view, any)
        } else {
            if (BuildConfig.DEBUG) throw NullPointerException("OnClick事件未绑定!")
        }
    }

    override fun onLongClick(view: View) {
        onLongClick(view, null)
    }

    override fun onLongClick(view: View, any: Any?) {
        if (mOnClickListener != null) {
            mOnClickListener?.invoke(view, any)
        } else {
            if (BuildConfig.DEBUG) throw NullPointerException("OnLongClick事件未绑定!")
        }
    }
}