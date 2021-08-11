package com.mredrock.cyxbs.mine.page.stamp.detail.fragment

import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.common.utils.extensions.startActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentExchangeRecordBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.ExchangeDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.detail.binder.ExchangeRecordBinder
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel

class ExchangeRecordFragment :
        BaseBindingSharedVMFragment<StampDetailViewModel, MineFragmentExchangeRecordBinding>() {

    private val mAdapter by lazy {
        binding?.rvExchange?.let { createMultiTypeAdapter(it, LinearLayoutManager(context)) }
    }

    override fun initView() {
        binding?.apply {
            setVariable(BR.viewModel, this)
            executePendingBindings()
        }
    }

    override fun observeData() {
        super.observeData()
        //观察rvList
        observeExchangeListData()
    }

    /**
     * @
     */
    private fun observeExchangeListData() {
        Log.e(TAG, "$shardViewModel")
        val clickHandler = ClickHandler()
        shardViewModel?.exchangeListData?.observe(this) {
            val binders: MutableList<MultiTypeBinder<*>> = mutableListOf()
            it.data.forEach {
                binders.add(ExchangeRecordBinder(it, clickHandler))
            }
            mAdapter?.notifyAdapterChanged(binders)
        }
    }


    override fun getLayoutId(): Int = R.layout.mine_fragment_exchange_record

    inner class ClickHandler {
        fun onClicked(v: View, any: Any?) {
            when (v.id) {
                R.id.cl_item -> {
                    onItemClicked(v, any)
                }
            }
        }

        private fun onItemClicked(v: View, any: Any?) {
            this@ExchangeRecordFragment.requireContext().startActivity<ExchangeDetailActivity>()
        }
    }
}