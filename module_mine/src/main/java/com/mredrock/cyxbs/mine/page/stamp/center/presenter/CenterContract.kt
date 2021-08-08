package com.mredrock.cyxbs.mine.page.stamp.center.presenter

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.presenter.IPresenter
import com.mredrock.cyxbs.common.viewmodel.IVM

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  16:11
 *@signature 好在键盘没坏。ha
 */
interface CenterContract {

    //VM层暴露的接口(也就是VM需要暴露给Presenter的所有数据)
    interface CenterVM : IVM {
        fun getIsTodayClicked(): Boolean
    }

    //内包含StampCenter界面的所有交互操作，也就是Presenter层需要暴露给View层的所有函数
    interface CenterPresenter : IPresenter<CenterVM>,
        TabLayoutMediator.TabConfigurationStrategy,
        TabLayout.OnTabSelectedListener {

    }

}