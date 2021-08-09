package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import android.os.Bundle
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityExchangeDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel

class ExchangeDetailActivity :
    BaseBindingViewModelActivity<ExchangeDetailViewModel, MineActivityExchangeDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initListener() {
        super.initListener()
        binding?.apply {
            fabBackArrow.setOnClickListener {
                finish()
            }
        }
        binding?.data = ExchangeDetailData(
            10000020, "待领取", "卷卷鼠标垫", 1000, "2021-2-25 14:32"
        )
    }

    override fun getLayoutId(): Int = R.layout.mine_activity_exchange_detail
}