package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentExchangeRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.binder.ExchangeRecordContainerBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class ExchangeRecordFragment : BaseBindingSharedVMFragment<StampDetailViewModel,MineFragmentExchangeRecordBinding>() {

    private val mAdapter by lazy {
        binding?.rvExchange?.let { createMultiTypeAdapter(it, LinearLayoutManager(context)) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.e(TAG, "$shardViewModel" )
        return binding?.root
    }

    override fun initView() {
        binding?.apply {
            setVariable(BR.viewModel,this)
            executePendingBindings()
        }
        setRecyclerViewContent()
    }

    private fun setRecyclerViewContent() {
        val list:MutableList<ExchangeItemData> = mutableListOf()
        list.apply {
            add(ExchangeItemData("卷卷鼠标垫","2030-1-1",4000,true))
            add(ExchangeItemData("卷卷鼠标垫","2030-1-1",4000,true))
            add(ExchangeItemData("卷卷鼠标垫","2030-1-1",4000,false))
        }
        mAdapter?.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            addAll((0..2).map {
                ExchangeRecordContainerBinder(list[it])
            })
        })
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment_exchange_record
    override fun getActivityVMClass(): Class<StampDetailViewModel> = StampDetailViewModel::class.java


}