package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import android.os.Bundle
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityExchangeDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel

class ExchangeDetailActivity :
    BaseBindingViewModelActivity<ExchangeDetailViewModel, MineActivityExchangeDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int = R.layout.mine_activity_exchange_detail
}