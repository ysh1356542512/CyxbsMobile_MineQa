package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGainRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.binder.GainRecordBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.model.GainListData
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class GainRecordFragment :
        BaseBindingSharedVMFragment<StampDetailViewModel, MineFragmentGainRecordBinding>() {

    private val mAdapter by lazy {
        binding?.rvGain?.let { createMultiTypeAdapter(it, LinearLayoutManager(context)) }
    }

    //设置布局
    override fun getLayoutId(): Int = R.layout.mine_fragment_gain_record

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
        }
    }

    override fun observeData() {
        super.observeData()
        shardViewModel?.apply {
            observeGainList(gainListData)
        }
    }

    private fun observeGainList(gainListData: LiveData<GainListData>) {
        gainListData.observe(this) {
            //映射数据
            val binders = it.data.map {
                GainRecordBinder(it)
            }
            //设置Adapter
            mAdapter?.notifyAdapterChanged(binders)
        }
    }
}