package com.mredrock.cyxbs.mine.page.stamp.center.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseDBViewModelFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentCenterShopBinding
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel


class CenterShopFragment : BaseDBViewModelFragment<GoodsViewModel,MineFragmentCenterShopBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.mine_fragment_center_shop
    }

}