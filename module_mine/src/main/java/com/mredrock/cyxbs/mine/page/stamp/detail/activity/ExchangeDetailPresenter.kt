package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.ExchangeDetailViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  11:27
 *@signature 我们不明前路，却已在路上
 */
class ExchangeDetailPresenter : BasePresenter<ExchangeDetailViewModel>(),StampDetailActivityContract.IVM {
    override fun fetch() {
        val content = getContent()
        vm?.setContent(content)
    }

    private fun getContent(): ExchangeDetailData {
        return ExchangeDetailData(
            System.currentTimeMillis(), "待领取", "卷卷鼠标垫", 1000, "2021-2-25 14:32"
        )
    }
}