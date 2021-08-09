package com.mredrock.cyxbs.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:11
 *@signature 我们不明前路，却已在路上
 */

/**
 * 注意这个ViewModel获取的是Activity的Context，其使用情况是创建共享activity内的ViewModel
 */
abstract class BaseMVPVMFragment<VM : BaseViewModel, T : ViewDataBinding, P : BasePresenter<*>> :
    BaseBindingSharedVMFragment<VM,T>(), IView {
    protected var presenter: P? = null


    //abstract fun createView():V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding?.lifecycleOwner = this

        presenter = createPresenter()
        //presenter?.onAttachView(this)
        shardViewModel?.let { presenter?.onAttachVM(it) }
        lifecycle.addObserver(presenter as LifecycleObserver)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //丢锅啦--Presenter
        fetch()
    }

    open fun fetch() {

    }


    abstract fun createPresenter(): P


    override fun onDestroy() {
        presenter?.let { lifecycle.removeObserver(it) }
        presenter?.onDetachVM()

        presenter?.clear()
        presenter = null
        super.onDestroy()
        //presenter?.detachView()
    }

}