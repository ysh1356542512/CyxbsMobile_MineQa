package com.mredrock.cyxbs.mine.page.stamp.center.fragment

import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.MultiTypeAdapter

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  20:15
 *@signature 好在键盘没坏。ha
 */
interface TaskContract  {
    interface TaskIPresenter{
        fun fetch()
        fun setAdapter(mAdapter: MultiTypeAdapter?)
    }

    interface TaskIVM{

    }
}