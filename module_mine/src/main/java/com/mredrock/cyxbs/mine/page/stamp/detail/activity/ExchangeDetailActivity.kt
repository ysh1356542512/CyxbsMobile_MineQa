package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import android.view.View
import androidx.lifecycle.LiveData
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityExchangeDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.config.DetailConfig
import com.mredrock.cyxbs.mine.page.stamp.config.DetailConfig.EXCHANGE_TO_DETAIL_KEY
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.detail.presenter.ExchangeDetailPresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.bean.exchange.ExchangeItemInfo
import java.lang.Exception
import java.lang.NullPointerException

class ExchangeDetailActivity :
        BaseMVPVMActivity<ExchangeDetailViewModel,
                MineActivityExchangeDetailBinding,
                ExchangeDetailPresenter>() {



    override fun getLayoutId(): Int = R.layout.mine_activity_exchange_detail

    override fun createPresenter(): ExchangeDetailPresenter =
            ExchangeDetailPresenter()

    override fun fetch() {
        super.fetch()
        try{
            presenter?.fetch(intent.getSerializableExtra(EXCHANGE_TO_DETAIL_KEY) as ExchangeItemInfo)
        }catch (e:NullPointerException){

        }
    }

    override fun initListener() {
        super.initListener()
        val eventHandler = EventHandler()
        binding?.apply {
            fabBackArrow.setOnClickListener(eventHandler::backArrowClicked)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel?.apply {
            observeContent(content)
        }
    }

    private fun observeContent(content: LiveData<ExchangeDetailData>) {
        content?.observe(this) {
            binding?.data = it
        }
    }

    inner class EventHandler {
        //返回键被点击
        fun backArrowClicked(view: View) {
            finish()
        }
    }
}
