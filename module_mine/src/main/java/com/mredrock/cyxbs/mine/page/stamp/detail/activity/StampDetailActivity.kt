package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.ExchangeRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.GainRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class StampDetailActivity :
    BaseMVPVMActivity<StampDetailViewModel, MineActivityStampDetailBinding,DetailPresenter>() {

    override fun initView() {
        binding?.eventHandler = EventHandler()
        initViewPagerAndTabs()
    }

    //初始化viewPager和Binding
    private fun initViewPagerAndTabs() {
        binding?.apply {
            //配置ViewPager的Adapter
            vpDetail.adapter = PagerAdapter(
                listOf(ExchangeRecordFragment(), GainRecordFragment()),
                this@StampDetailActivity
            )
            //ViewPager和TabLayout联动
            TabLayoutMediator(tlDetail, vpDetail) { tb, position ->
                when (position) {
                    0 -> {
                        tb.text = "兑换记录"
                    }
                    1 -> {
                        tb.text = "获取记录"
                    }
                }
            }.attach()
        }
    }

    override fun observeData() {
        super.observeData()
    }

    override fun fetch() {
        super.fetch()

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