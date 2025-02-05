package com.mredrock.cyxbs.mine.page.stamp.center.activity

import androidx.fragment.app.FragmentActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.presenter.IPresenter
import com.mredrock.cyxbs.common.viewmodel.IVM
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopPageData
import com.mredrock.cyxbs.mine.page.stamp.center.model.StampTaskData

/**
 *@author ZhiQiang Tu
 *@time 2021/8/8  16:11
 *@signature 好在键盘没坏。ha
 */
interface StampCenterContract {

    //VM层暴露的接口(也就是VM需要暴露给Presenter的所有数据)
    interface CenterVM : IVM {
        fun setTasksValue(value: StampTaskData)
        fun setShopPageDataValue(value: ShopPageData)
        fun setUserAccount(value:Int)
        fun setHasGoodsToGet(value:Boolean)
    }

    //内包含StampCenter界面的所有交互操作，也就是Presenter层需要暴露给View层的所有函数
    interface CenterPresenter : IPresenter<StampCenterViewModel>,
            TabLayoutMediator.TabConfigurationStrategy,
            TabLayout.OnTabSelectedListener {
        fun initVP2(
                fgActivity: FragmentActivity, vpCenter: ViewPager2, func: () -> Unit
        )
        fun setDefaultPageData()
        fun refresh(srlRefresh: SwipeRefreshLayout)
    }

}