package com.mredrock.cyxbs.common.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mredrock.cyxbs.common.component.CyxbsToast
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.common.viewmodel.event.ProgressDialogEvent
import com.umeng.commonsdk.debug.D
import java.lang.reflect.ParameterizedType




abstract class BaseDBViewModelFragment<T : BaseViewModel,D:ViewDataBinding> : BaseFragment() {
    protected lateinit var viewModel: T

    protected lateinit var binding: D


    private var progressDialog: ProgressDialog? = null

    private fun initProgressBar() = ProgressDialog(context).apply {
        isIndeterminate = true
        setMessage("Loading...")
        setOnDismissListener { viewModel.onProgressDialogDismissed() }
    }

    abstract fun getLayoutResId():Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutResId(),container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = getViewModelFactory()
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        viewModel = if (viewModelFactory != null) {
            ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProvider(this).get(viewModelClass)
        }

        viewModel.apply {
            toastEvent.observe { str -> str?.let { CyxbsToast.makeText(context, it, Toast.LENGTH_SHORT).show() } }
            longToastEvent.observe { str -> str?.let { CyxbsToast.makeText(context, it, Toast.LENGTH_LONG).show() } }
            progressDialogEvent.observe {
                it ?: return@observe
                // 确保只有一个对话框会被弹出
                if (it != ProgressDialogEvent.DISMISS_DIALOG_EVENT && progressDialog?.isShowing != true) {
                    progressDialog = progressDialog ?: initProgressBar()
                    progressDialog?.show()
                } else if (it == ProgressDialogEvent.DISMISS_DIALOG_EVENT && progressDialog?.isShowing != false) {
                    progressDialog?.dismiss()
                }
            }
        }
    }

    protected open fun getViewModelFactory(): ViewModelProvider.Factory? = null

    inline fun <T> LiveData<T>.observe(crossinline onChange: (T?) -> Unit) = observe(this@BaseDBViewModelFragment, Observer { onChange(value) })
    inline fun <T> LiveData<T>.observeNotNull(crossinline onChange: (T) -> Unit) = observe(this@BaseDBViewModelFragment, Observer {
        it ?: return@Observer
        onChange(it)
    })

    override fun onDestroyView() {
        super.onDestroyView()
        if (progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
        }
    }
}