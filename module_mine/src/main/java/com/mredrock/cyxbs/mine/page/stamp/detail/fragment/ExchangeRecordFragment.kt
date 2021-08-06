package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentExchangeRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class ExchangeRecordFragment : BaseBindingSharedVMFragment<StampDetailViewModel,MineFragmentExchangeRecordBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.e(TAG, "$shardViewModel" )
        return binding?.root
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment_exchange_record
    override fun getViewModelClass(): Class<StampDetailViewModel> = StampDetailViewModel::class.java


}