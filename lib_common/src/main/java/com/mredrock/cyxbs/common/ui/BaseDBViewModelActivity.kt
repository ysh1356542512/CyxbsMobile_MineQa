package com.mredrock.cyxbs.common.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mredrock.cyxbs.common.component.CyxbsToast
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.common.viewmodel.event.ProgressDialogEvent
import java.lang.reflect.ParameterizedType

/**
* @Date : 2021/8/5
* @By : ysh
* @Usage : 基于BaseViewModelActivity上实现的获得DataBinding和ViewModel的Base类
* @Request : God bless my code
*/
abstract class BaseDBViewModelActivity<T : BaseViewModel,D:ViewDataBinding> : BaseActivity() {
    lateinit var viewModel: T

    protected val binding: D by lazy {
        //使用反射得到viewbinding的class
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as D
    }


    private var progressDialog: ProgressDialog? = null

    private fun initProgressBar() = ProgressDialog(this).apply {
        isIndeterminate = true
        setMessage("Loading...")
        setOnDismissListener { viewModel.onProgressDialogDismissed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val viewModelFactory = getViewModelFactory()
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        viewModel = if (viewModelFactory != null) {
            ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProvider(this).get(viewModelClass)
        }
        viewModel.apply {
            toastEvent.observe { str -> str?.let { CyxbsToast.makeText(baseContext, it, Toast.LENGTH_SHORT).show() } }
            longToastEvent.observe { str -> str?.let { CyxbsToast.makeText(baseContext, it, Toast.LENGTH_LONG).show() } }
            progressDialogEvent.observe {
                it ?: return@observe
                //确保只有一个对话框会被弹出
                if (it != ProgressDialogEvent.DISMISS_DIALOG_EVENT && progressDialog?.isShowing != true) {
                    progressDialog = progressDialog ?: initProgressBar()
                    progressDialog?.show()
                } else if (it == ProgressDialogEvent.DISMISS_DIALOG_EVENT && progressDialog?.isShowing != false) {
                    progressDialog?.dismiss()
                }
            }
        }

        //统一加载view
        initView()

        //统一数据监听
        observeData()

        //统一监听器
        initListener()
    }

    open fun initView(){}

    open fun observeData(){}

    open fun initListener(){}

    protected open fun getViewModelFactory(): ViewModelProvider.Factory? = null

    inline fun <T> LiveData<T>.observe(crossinline onChange: (T?) -> Unit) = observe(this@BaseDBViewModelActivity, Observer { onChange(it) })

    inline fun <T> LiveData<T>.observeNotNull(crossinline onChange: (T) -> Unit) = observe(this@BaseDBViewModelActivity, Observer {
        it ?: return@Observer
        onChange(it)
    })

    override fun onDestroy() {
        super.onDestroy()
        if (progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
        }
    }
}
