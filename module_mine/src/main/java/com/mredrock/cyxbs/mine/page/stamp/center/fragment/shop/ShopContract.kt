package com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop

import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductOne
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductTwo

/**
 * @Date : 2021/8/9
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
interface ShopContract {
    interface ShopIPresenter {
        fun mapDoubleCardToOne(
                indices: IntRange,
                decorator: List<ShopProductOne>,
        ): List<ShopProductTwo>
    }

    interface ShopIVM {}
}