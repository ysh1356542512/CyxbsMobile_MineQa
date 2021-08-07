package com.mredrock.cyxbs.mine.page.stamp.center.activity

import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampCenterBinding
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.StampTaskFragment
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.ExchangeDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.StampDetailActivity
import kotlinx.android.synthetic.main.mine_activity_stamp_center.*

/**
* @Date : 2021/8/4
* @By : ysh
* @Usage : 邮票中心主界面
* @Request : God bless my code
*/
class StampCenterActivity : BaseBindingViewModelActivity<StampCenterViewModel, MineActivityStampCenterBinding>() {

    //用于记录今天是否已经点击小店
    private var isClickToday = false

    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_center

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.vm = viewModel

    }

//        Log.e(TAG, "$viewModel $binding" )


    override fun initView() {
        //先进行 viewPager2 和 tablayout 的绑定
        binding?.apply {
            vpCenter.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            val fragments = arrayListOf<Fragment>(CenterShopFragment(), StampTaskFragment())
            vpCenter.adapter = object : FragmentStateAdapter(this@StampCenterActivity) {
                override fun getItemCount(): Int {
                    return 2
                }


                override fun createFragment(position: Int): Fragment {
                    return fragments[position]
                }
            }
            vpCenter.apply {
                offscreenPageLimit = 2
                //此处可动态设置tabItem布局
                //关于tablayout代码的设计 这样能够尽可能的减少网络请求的次数
                TabLayoutMediator(tlCenter, vpCenter, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
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
                }).attach()

            }
            tlCenter.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //先判断isClickToday是否为true 若为true 只切换 为false 则Post提交数据 表示今日第一次点击 并将小红点GONE或者重新换布局
                    when (!isClickToday) {
                        true -> {
                            when (tab?.position) {
                                1 -> {
                                    //在这个点卡了比较久 因为发现如果只setCustomView一次其实 tab 不会去更换布局 而是会类似于解绑布局 导致切换tab直接不显示布局内容
                                    //所以在这里调用两次 setCustomView 一次用来解除布局绑定 第二次来重新绑定来实现切换布局
                                    tab.setCustomView(R.layout.mine_item_tab_click)
                                    tab.setCustomView(R.layout.mine_item_tab_click)
                                    //在这里POST数据 并将isClickToday为true表示已经点击
                                    isClickToday = true
                                }
                                else -> {

                                }
                            }
                        }
                        else -> {
                        }
                    }
                }

            })
        }
    }

    override fun initListener() {
        binding?.apply {
            ivCenterBack.setOnSingleClickListener {
                onBackPressed()
            }
            includeCenterPart2.mineCenterPartThree.ivCenterDetail.setOnSingleClickListener { startActivity<StampDetailActivity>() }
            includeCenterPart2.mineCenterPartThree.tvCenterDetail.setOnSingleClickListener { startActivity<StampDetailActivity>() }
            //这个到时候可能会跳转至订单详情页 需要在 ExchangeDetailActivity中再加一个方法来跳转到详情页
            includeCenterPart2.mineCenterPartThree.tvCenterCommend.setOnSingleClickListener { startActivity<ExchangeDetailActivity>() }
        }
    }



}