package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

import android.view.View
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.presenter.IPresenter
import com.mredrock.cyxbs.common.viewmodel.IVM
//import com.mredrock.cyxbs.mine.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel

/**
 * @Date : 2021/8/11   9:58
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
//接口的作用更倾向于让接管者更清晰地知道P层有哪些函数 分别有什么作用 也就是P层在干的活有哪些
interface GoodsContract {
    interface GoodsVM : IVM {
        fun setGoodsValue(value: GoodsInfo.Data)
        fun setGoodsType(value: String)
        fun setDescription(value: String, value2: String)
        fun setGoodsDate(value: String)
        fun setGoodsUrls(value: List<String>)
        fun setGoodsAmount(value: Int)
        fun setUserAccount(value:Int)
    }

    interface GoodsPresenter : IPresenter<GoodsViewModel> {
        fun initBVP(bvpViewPager: BannerViewPager<String>, lifecycle: Lifecycle,list: List<String>, func: (Int, View) -> Unit)
        fun setDefaultData()
    }
}