package com.mredrock.cyxbs.mine.page.stamp.center.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampCenterBinding
import com.mredrock.cyxbs.mine.page.stamp.center.animation.DepthPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.center.animation.ZoomOutPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.ITaskView
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.StampTaskFragment
import com.mredrock.cyxbs.mine.page.stamp.center.presenter.StampCenterPresenter
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.ExchangeDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.StampDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import kotlinx.android.synthetic.main.mine_activity_stamp_center.*


/*
 * @Date : 2021/8/4
 * @By : ysh
 * @Usage : 邮票中心主界面
 * @Request : God bless my code
 */

class StampCenterActivity :
    BaseMVPVMActivity<StampCenterViewModel, MineActivityStampCenterBinding, StampCenterPresenter>(),
    ITaskView {


    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_center

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.vm = viewModel
    }


    override fun initView() {
        //先进行 viewPager2 和 TabLayout 的绑定
        binding?.apply {
//            context = this@StampCenterActivity
            vpCenter.setPageTransformer(ZoomOutPageTransformer())
            vpCenter.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            val fragments = arrayListOf<Fragment>(CenterShopFragment(), StampTaskFragment())
            //设置Adapter
            vpCenter.adapter = PagerAdapter(fragments, this@StampCenterActivity)

            vpCenter.apply {
                offscreenPageLimit = 2
                //此处可动态设置tabItem布局
                //关于tablayout代码的设计 这样能够尽可能的减少网络请求的次数
                presenter?.let {
                    TabLayoutMediator(
                        tlCenter,
                        vpCenter,
                        it
                    ).attach()
                }
            }
            presenter?.let { tlCenter.addOnTabSelectedListener(it) }
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

    override fun createPresenter(): StampCenterPresenter = StampCenterPresenter()
}