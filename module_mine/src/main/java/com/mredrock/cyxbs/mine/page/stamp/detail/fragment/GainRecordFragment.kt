package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGainRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.binder.GainRecordBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class GainRecordFragment :
    BaseBindingSharedVMFragment<StampDetailViewModel, MineFragmentGainRecordBinding>() {

    private val mAdapter by lazy {
        binding?.rvGain?.let { createMultiTypeAdapter(it, LinearLayoutManager(context)) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding?.root
    }

    override fun initView() {
        binding?.apply {
            rvGain.apply {
                setVariable(BR.viewModel, this)
                executePendingBindings()
            }
            setRecyclerContent()
        }
    }

    private fun setRecyclerContent() {
        val list: List<ExchangeItemData> = listOf(
            ExchangeItemData("游览任务", "2030-1-1", 40, false),
            ExchangeItemData("游览任务", "2030-1-1", 40, false)
        )
        mAdapter?.notifyAdapterChanged(
            (0..1).map {
                GainRecordBinder(list[it])
            }
        )
    }

    override fun initConfiguration() {

    }

    //设置布局
    override fun getLayoutId(): Int = R.layout.mine_fragment_gain_record

    //设置ViewModel
    override fun getActivityVMClass(): Class<StampDetailViewModel> =
        StampDetailViewModel::class.java


}