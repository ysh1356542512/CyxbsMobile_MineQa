package com.mredrock.cyxbs.mine.page.stamp.center.presenter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.presenter.IPresenter
import com.mredrock.cyxbs.common.viewmodel.IVM
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterActivity
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  16:11
 *@signature 好在键盘没坏。ha
 */
interface CenterContract {

    //VM层暴露的接口(也就是VM需要暴露给Presenter的所有数据)
    interface CenterVM : IVM {
        fun setIsClickToday(value: Boolean)
        fun getIsClickToday(): Boolean
        fun setTasksValue(value: StampTaskData)
    }

    //内包含StampCenter界面的所有交互操作，也就是Presenter层需要暴露给View层的所有函数
    interface CenterPresenter : IPresenter<StampCenterViewModel>,
        TabLayoutMediator.TabConfigurationStrategy,
        TabLayout.OnTabSelectedListener {
        fun initVP2(
            fgActivity: FragmentActivity, vpCenter: ViewPager2, func: () -> Unit
        )
    }

}