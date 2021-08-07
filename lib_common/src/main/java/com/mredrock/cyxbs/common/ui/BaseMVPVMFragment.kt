package com.mredrock.cyxbs.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:11
 *@signature 我们不明前路，却已在路上
 */
abstract class BaseMVPVMFragment<VM : BaseViewModel, T : ViewDataBinding, P : BasePresenter<*>> :
    BaseViewModelFragment<VM>(), IView {
    protected var presenter: P? = null
    protected var binding: T? = null
    protected var shardViewModel: VM? = null


    abstract fun getLayoutId(): Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding?.lifecycleOwner = this

        presenter = createPresenter()
        presenter?.onAttachView(this)

        lifecycle.addObserver(presenter as LifecycleObserver)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化配置
        initConfiguration()

        //初始化view
        initView()

        //初始化监听器
        initListener()

        //初始化数据监听
        observeData()
    }


    abstract fun createPresenter(): P

    abstract fun getActivityVMClass(): Class<VM>

    //abstract fun createView():V

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

    open fun initView() {}

    open fun initListener() {}

    open fun observeData() {}

    open fun initConfiguration() {}

}