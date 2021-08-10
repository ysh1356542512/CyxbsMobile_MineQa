package com.mredrock.cyxbs.mine.page.stamp.detail.activity


import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.center.animation.DepthPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.ExchangeRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.GainRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel
import kotlinx.android.synthetic.main.mine_activity_stamp_detail.*

class StampDetailActivity :
        BaseMVPVMActivity<StampDetailViewModel, MineActivityStampDetailBinding, DetailPresenter>() {

    override fun initView() {
        binding?.eventHandler = EventHandler()
        initViewPagerAndTabs()
    }

    //初始化viewPager和Binding
    private fun initViewPagerAndTabs() {
        binding?.apply {
            //配置ViewPager的Adapter
            vpDetail.setPageTransformer(DepthPageTransformer())
            vpDetail.adapter =
                    PagerAdapter(
                            listOf(ExchangeRecordFragment(), GainRecordFragment()),
                            this@StampDetailActivity
                    )

            //ViewPager和TabLayout联动
            TabLayoutMediator(tlDetail, vpDetail) { tb, position ->
                when (position) {
                    0 -> {
                        tb.text = "兑换记录"
                        tb.view.scaleX = 1.12f
                        tb.view.scaleY = 1.12f
                    }
                    1 -> {
                        tb.text = "获取记录"

                    }
                }
            }.attach()
            tlDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                @SuppressLint("ObjectAnimatorBinding")
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val animatorX = ObjectAnimator.ofFloat(tab?.view, "scaleX", 1f, 1.12f)
                    val animatorY = ObjectAnimator.ofFloat(tab?.view, "scaleY", 1f, 1.12f)
                    animatorX.duration = 800
                    animatorY.duration = 800
                    animatorX.start()
                    animatorY.start()
                }

                @SuppressLint("ObjectAnimatorBinding")
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val animatorX = ObjectAnimator.ofFloat(tab?.view, "scaleX", 1.12f, 1f)
                    val animatorY = ObjectAnimator.ofFloat(tab?.view, "scaleY", 1.12f, 1f)
                    animatorX.duration = 800
                    animatorY.duration = 800
                    animatorX.start()
                    animatorY.start()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }

        Log.e(TAG, "$viewModel")
    }

    override fun fetch() {
        super.fetch()
        presenter?.fetch()
    }

    //设置布局
    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_detail


    inner class EventHandler {
        fun backArrowClick(view: View) {
            this@StampDetailActivity.finish()
        }
    }

    override fun createPresenter(): DetailPresenter = DetailPresenter()
}