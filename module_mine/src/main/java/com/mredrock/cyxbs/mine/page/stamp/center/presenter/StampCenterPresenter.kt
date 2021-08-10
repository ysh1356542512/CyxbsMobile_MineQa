package com.mredrock.cyxbs.mine.page.stamp.center.presenter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.animation.ZoomOutPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.StampTaskFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter


/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:51
 *@signature 我们不明前路，却已在路上
 */
private const val TAG = "StampCenterPresenter"

class StampCenterPresenter : BasePresenter<CenterContract.CenterVM>(),
    CenterContract.CenterPresenter {
    //ViewPager与TabLayout的联动部分
    //TabLayoutMediator.TabConfigurationStrategy
    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        when (position) {
            0 -> {
                //此处setCustomView来设置布局 tab.setCustomView(R.layout.xxx)
                //在TabLayoutMediator源码的populateTabsFromPagerAdapter方法中可查看逻辑
                tab.setCustomView(R.layout.mine_item_tab_shop)
            }
            else -> {
                //邮票任务在此处进行网络申请 若该日未点击过 则设置带有小红点的布局(在之前统一申请两个fragment和该activity的所有数据)
                //因为每次加载该Activity的时候都要经过此代码 为了减少onTabSelected处网络申请的次数
                //定义一个boolean类型的成员变量isClickToday来供判断 若此处返回的结果表示用户今日已点击过 则为true
                //得到网络申请 若为今日未点击 加载布局 isClickToday = true 若已点击 加载另一个布局isClickToday = false
                tab.setCustomView(R.layout.mine_item_tab_task_no_click)
            }
        }
    }

    //tab
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        tab?.view?.alpha = 0.5f
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val isClickToday = vm?.getIsClickToday() ?: false
        //先判断isClickToday是否为true 若为true 只切换 为false 则Post提交数据 表示今日第一次点击 并将小红点GONE或者重新换布局
        when (!isClickToday) {
            true -> {
                when (tab?.position) {
                    1 -> {
                        //tab.orCreateBadge
                        //在这个点卡了比较久 因为发现如果只setCustomView一次其实 tab 不会去更换布局 而是会类似于解绑布局 导致切换tab直接不显示布局内容
                        //所以在这里调用两次 setCustomView 一次用来解除布局绑定 第二次来重新绑定来实现切换布局
                        tab.setCustomView(R.layout.mine_item_tab_click)
                        tab.setCustomView(R.layout.mine_item_tab_click)
                        tab.view.alpha = 1.0f
                        vm?.setIsClickToday(true)
                        //在这里POST数据 并将isClickToday为true表示已经点击
                    }
                    else -> {
                        tab?.view?.alpha = 1.0f
                    }
                }
            }
            else -> {
                tab?.view?.alpha = 1.0f
            }
        }
    }

    override fun initVP2(fgActivity: FragmentActivity, vpCenter: ViewPager2, func: () -> Unit) {
        vpCenter.apply {
            setPageTransformer(ZoomOutPageTransformer())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            val fragments = arrayListOf<Fragment>(CenterShopFragment(), StampTaskFragment())
            adapter = PagerAdapter(fragments, fgActivity)
            func()
        }
    }

    override fun fetch() {

    }


}
