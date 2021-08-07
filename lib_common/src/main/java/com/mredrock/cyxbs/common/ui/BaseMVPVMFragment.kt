package com.mredrock.cyxbs.common.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:11
 *@signature 我们不明前路，却已在路上
 */
abstract class BaseMVPVMFragment< VM : BaseViewModel, T : ViewDataBinding,P : BasePresenter<*>> :
    BaseBindingSharedVMFragment<VM, T>(),IView {
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.onAttachView(this)
    }

    abstract fun createPresenter():P

    //abstract fun createView():V

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }
}