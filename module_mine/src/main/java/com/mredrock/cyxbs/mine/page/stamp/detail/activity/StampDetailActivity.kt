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
import com.mredrock.cyxbs.mine.page.stamp.detail.presenter.DetailPresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel
import kotlinx.android.synthetic.main.mine_activity_stamp_detail.*

class StampDetailActivity :
        BaseMVPVMActivity<StampDetailViewModel, MineActivityStampDetailBinding, DetailPresenter>() {
    /**
     * 布局信息
     */
    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_detail

    /**
     * P层信息
     */
    override fun createPresenter(): DetailPresenter = DetailPresenter()

    /**
     * 获取数据
     */
    override fun fetch() {
        super.fetch()
        presenter?.fetch()
    }

    /**
     * 初始化view
     */
    override fun initView() {
        binding?.eventHandler = EventHandler()
        initViewPagerAndTabs()
        presenter?.getDefaultData()
    }

    /**
     * 初始化viewPager和Binding
     */
    private fun initViewPagerAndTabs() {
        binding?.apply {
            //配置ViewPager的Adapter
            presenter?.let {
                it.initViewPagerAndTabs(this@StampDetailActivity,vpDetail){
                    TabLayoutMediator(tlDetail,vpDetail,it).attach()
                }
                tlDetail.addOnTabSelectedListener(it)
            }
        }
    }

    inner class EventHandler {
        fun backArrowClick(view: View) {
            this@StampDetailActivity.finish()
        }
    }


}