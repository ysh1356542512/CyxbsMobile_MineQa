package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.common.utils.extensions.startActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentExchangeRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.ExchangeDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.detail.binder.ExchangeRecordBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.model.DetailItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class ExchangeRecordFragment :
    BaseBindingSharedVMFragment<StampDetailViewModel, MineFragmentExchangeRecordBinding>() {

    private val mAdapter by lazy {
        binding?.rvExchange?.let { createMultiTypeAdapter(it, LinearLayoutManager(context)) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.e(TAG, "$shardViewModel")
        return binding?.root
    }

    override fun initView() {
        binding?.apply {
            setVariable(BR.viewModel, this)
            executePendingBindings()
        }
        setRecyclerViewContent()
    }

    private fun setRecyclerViewContent() {
        val handler:ClickHandler = ClickHandler()
        val list: MutableList<DetailItemData> = mutableListOf()
        list.apply {
            add(DetailItemData("卷卷鼠标垫", "2030-1-1", 4000, true))
            add(DetailItemData("卷卷鼠标垫", "2030-1-1", 4000, true))
            add(DetailItemData("卷卷鼠标垫", "2030-1-1", 4000, false))
        }
        mAdapter?.notifyAdapterChanged(
            (0..2).map { it ->
            ExchangeRecordBinder(list[it],handler)
        })
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment_exchange_record
    override fun getActivityVMClass(): Class<StampDetailViewModel> =
        StampDetailViewModel::class.java

    inner class ClickHandler(){
        fun onClicked(v:View,any: Any?){
            when(v.id){
                R.id.cl_item->{
                    onItemClicked(v,any)
                }
            }
        }

        private fun onItemClicked(v: View, any: Any?) {
            this@ExchangeRecordFragment?.requireContext()?.startActivity<ExchangeDetailActivity>()
        }
    }
}