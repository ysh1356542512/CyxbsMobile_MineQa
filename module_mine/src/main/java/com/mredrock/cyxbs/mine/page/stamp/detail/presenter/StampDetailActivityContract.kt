package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

//import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.GainInfo
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo

/**
* @Date : 2021/8/13
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
interface StampDetailActivityContract {

    interface IPresenter  :
            TabLayoutMediator.TabConfigurationStrategy,
            TabLayout.OnTabSelectedListener{
        //得到兑换详情
        //得到明细详情
        fun getGainData(func: (GainInfo) -> Unit)
        fun getExchangeData(func: (ExchangeInfo) -> Unit)
        fun getDefaultData()
        fun initViewPagerAndTabs(fgActivity: FragmentActivity, vpDetail: ViewPager2, func: () -> Unit)
    }

    interface IVM {
        fun setExchangeListDataValue(value: ExchangeInfo)
        fun setGainListDataValue(value: GainInfo)
    }
}