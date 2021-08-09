package com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop

import androidx.lifecycle.LiveData
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopInfo

/**
 * @Date : 2021/8/9
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
interface ShopContract {
    interface ShopIPresenter{

    }
    interface ShopIVM{
        fun getShopInfoList():LiveData<List<ShopInfo>>
    }
}