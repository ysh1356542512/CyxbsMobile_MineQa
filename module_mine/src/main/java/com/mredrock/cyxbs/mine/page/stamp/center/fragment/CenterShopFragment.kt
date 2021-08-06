package com.mredrock.cyxbs.mine.page.stamp.center.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentCenterShopBinding
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealContainerBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback.OnViewClickListener
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.model.ExchangeItemData


class CenterShopFragment : BaseBindingViewModelFragment<StampCenterViewModel,MineFragmentCenterShopBinding>(),OnViewClickListener {

    private val mAdapter by lazy {
        binding?.rvShopReal?.let { createMultiTypeAdapter(it,LinearLayoutManager(context)) }
    }

    override fun getLayoutId(): Int {
        return R.layout.mine_fragment_center_shop
    }

    override fun initView() {
        binding?.apply {
            setVariable(BR.viewModel, this)
            executePendingBindings()
            executePendingBindings()
        }
        setRecyclerViewContent()
    }

    private fun setRecyclerViewContent(){
        mAdapter?.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            add(
                GoodsRealContainerBinder((1..20).map{ GoodsRealBinder(it).apply {
                setOnClickListener(this@CenterShopFragment::onClick)
            }
            })
            )
        })
    }

    override fun onClick(view: View, any: Any?) {
        when(view.id){

        }
    }

}