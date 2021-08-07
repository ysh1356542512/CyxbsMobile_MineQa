package com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.mredrock.cyxbs.mine.R

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : BaseBinder类 与adapter里的binder类等价
 * @Request : God bless my code
 */
abstract class MultiTypeBinder<V : ViewDataBinding> : ClickBinder() {


    //没什么用的 就是利用DataBinding产生的BR类来得到一个常量和Activity的对应就行 相当于钥匙吧
    protected open val variableId = BR.viewModel

    open var binding: V? = null

    //设置tag 判断dataBinding有没有重复绑定
    private var bindingViewVersion = (0L until Long.MAX_VALUE).random()

    //传布局id
    abstract fun layoutId(): Int

    abstract fun areContentsTheSame(other: Any): Boolean

    fun bindViewDataBinding(binding: V) {
        //若已经绑定 则返回不绑定
        if (this.binding == binding && binding.root.getTag(R.id.mine_bindingVersion) == bindingViewVersion) return
        binding.root.setTag(R.id.mine_bindingVersion, ++bindingViewVersion)

        onUnBindViewHolder()
        this.binding = binding
        binding.setVariable(variableId, this)
        //在内部实现双向绑定 对LiveData进行观察
        if (binding.root.context is LifecycleOwner) {
            binding.lifecycleOwner = binding.root.context as LifecycleOwner
        } else {
            binding.lifecycleOwner = AlwaysActiveLifecycleOwner()
        }
        onBindViewHolder(binding)
        binding.executePendingBindings()

    }

    fun unbindDataBinding() {
        if (this.binding != null) {
            onUnBindViewHolder()
            this.binding = null
        }
    }

    protected open fun onBindViewHolder(binding: V) {

    }

    protected open fun onUnBindViewHolder() {}

    //绑定生命周期
    internal class AlwaysActiveLifecycleOwner : LifecycleOwner {

        override fun getLifecycle(): Lifecycle = object : LifecycleRegistry(this) {
            init {
                //只有当resume的时候才会响应 也就是活跃时才响应
                handleLifecycleEvent(Event.ON_RESUME)
            }
        }
    }


}