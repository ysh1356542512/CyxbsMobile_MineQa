package com.mredrock.cyxbs.common.presenter

import androidx.lifecycle.*
import com.mredrock.cyxbs.common.model.IModel
import com.mredrock.cyxbs.common.ui.IView
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import java.lang.ref.WeakReference

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  11:51
 *@signature 我们不明前路，却已在路上
 */
abstract class BasePresenter</*V : IView,*/ VM : BaseViewModel> : LifecycleObserver {
    protected var vm: VM? = null

    /*protected var view: WeakReference<V>? = null
    fun onAttachView(view: IView) {
        this.view = WeakReference(view as V)
    }
    fun detachView() {
        this.view?.clear()
    }*/

    fun onAttachVM(vm:BaseViewModel){
        this.vm = vm as VM
    }

    fun detachVM(){
        vm = null
    }

    //生命周期方法
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected fun onCreate(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected fun onResume(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected fun onStart(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected fun onPause(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onStop(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroy(lifecycleOwner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected fun onAny(lifecycleOwner: LifecycleOwner) {
    }


}