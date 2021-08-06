package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.adapter.DetailPagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.ExchangeRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.GainRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class StampDetailActivity :
    BaseBindingViewModelActivity<StampDetailViewModel, MineActivityStampDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "$viewModel" )
    }

    override fun initView() {
        binding?.lifecycleOwner = this

        binding?.apply {
            //配置ViewPager的Adapter
            vpDetail.adapter =
                DetailPagerAdapter(
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

    //设置布局
    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_detail
}