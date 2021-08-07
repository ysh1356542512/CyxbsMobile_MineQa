package com.mredrock.cyxbs.common.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import java.lang.ref.WeakReference

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  11:38
 *@signature 我们不明前路，却已在路上
 */
abstract class BaseMVPVMActivity<VM : BaseViewModel, T : ViewDataBinding,P : BasePresenter<*>> :
    BaseViewModelActivity<VM>() , IView {

    protected var presenter: P? = null

    abstract fun createPresenter(): P

    protected var binding:T? = null

    abstract fun getLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,getLayoutId())
        binding?.lifecycleOwner = this

        //创建一个弱引用的Presenter
        presenter = createPresenter()
        //建立联系
        presenter?.onAttachView(this)
        //双向关联成功

        lifecycle.addObserver(presenter as LifecycleObserver)

        //初始化view
        initView()
        //初始化监听器
        initListener()
        //初始化数据监听
        observeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter中的引用
        presenter?.detachView()
        //解除自己对presenter的引用
        presenter = null
        //activity凉了
    }

    open fun initView(){
    }

    open fun initListener() {}

    open fun observeData(){}

}