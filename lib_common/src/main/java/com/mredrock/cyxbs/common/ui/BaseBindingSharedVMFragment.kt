package com.mredrock.cyxbs.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  14:21
 *@signature 我们不明前路，却已在路上
 */
abstract class BaseBindingSharedVMFragment<VM : ViewModel, T : ViewDataBinding> : BaseFragment() {
    var binding: T? = null
    var shardViewModel: VM? = null

    abstract fun getLayoutId(): Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding?.lifecycleOwner = this
        shardViewModel = ViewModelProvider(requireActivity()).get(getActivityVMClass())
        //初始化view
        initView()

        //初始化监听器
        initListener()

        //初始化数据监听
        observeData()

        return binding?.root


    }

    abstract fun getActivityVMClass(): Class<VM>

    open fun initView() {}

    open fun initListener() {}

    open fun observeData() {}
}