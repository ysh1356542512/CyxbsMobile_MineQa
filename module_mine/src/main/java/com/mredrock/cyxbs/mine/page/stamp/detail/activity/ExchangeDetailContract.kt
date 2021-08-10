package com.mredrock.cyxbs.mine.page.stamp.detail.activity

import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeDetailData

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  11:29
 *@signature 好在键盘没坏。ha
 */
interface ExchangeDetailContract {
    interface IPresenter{

    }

    interface IVM{

        fun setContent(value: ExchangeDetailData)
    }
}