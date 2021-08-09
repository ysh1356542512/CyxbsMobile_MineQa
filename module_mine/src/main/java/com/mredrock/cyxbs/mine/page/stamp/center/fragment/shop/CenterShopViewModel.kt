package com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopInfo


/**
 * @Date : 2021/8/9
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class CenterShopViewModel : BaseViewModel(), ShopContract.ShopIVM {

    private val _shopInfo: MutableLiveData<List<ShopInfo>> = MutableLiveData(listOf(
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "挂件挂件挂件挂件挂件", 0, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111"),
            ShopInfo(20, 111, 121, "邮物邮物邮物邮物邮物", 1, "111")))

    private val shopInfo:LiveData<List<ShopInfo>>
    get() = _shopInfo

    override fun getShopInfoList(): LiveData<List<ShopInfo>> {
        return shopInfo
    }


}