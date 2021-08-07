package com.mredrock.cyxbs.mine.page.stamp.center.fragment

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelFragment
import com.mredrock.cyxbs.common.utils.extensions.startActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentCenterShopBinding
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealContainerBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback.OnViewClickListener
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.activity.GoodsActivity
import kotlinx.android.synthetic.main.mine_item_stamp_goods.*


class CenterShopFragment : BaseBindingViewModelFragment<StampCenterViewModel,MineFragmentCenterShopBinding>(),OnViewClickListener {

    //初始化adapter
    private val mAdapter by lazy {
        binding?.rvShopReal?.let { createMultiTypeAdapter(it,LinearLayoutManager(context)) }
    }

    override fun getLayoutId(): Int {
        return R.layout.mine_fragment_center_shop
    }

    override fun initView() {
        binding?.apply {
            //设置类似key的东西 判断binding是否已经绑定
            setVariable(BR.viewModel, this)
            executePendingBindings()
            executePendingBindings()
        }
        //设置内容
        setRecyclerViewContent()
    }

    private fun setRecyclerViewContent(){
        //在这里可以add很多个类型的ContainerBinder
        mAdapter?.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            //用map函数给每一个Binder设置监听事件 具体事件在这个类里的onClick函数定义 根据id来判断binder
            add(GoodsRealContainerBinder((1..20)
                    .filter {
                        it>0
                    }.map{
                        GoodsRealBinder(it).apply {
                setOnClickListener(this@CenterShopFragment::onClick)
            }
            },"装扮"))
            add(GoodsRealContainerBinder((1..20).map{
                GoodsRealBinder(it).apply {
                setOnClickListener(this@CenterShopFragment::onClick)
            }
            },"邮物"))
        })
    }

    override fun onClick(view: View, any: Any?) {
        when(view.id){
            R.id.goods_container->{
                any as GoodsRealBinder
                toast(view,"点击${any.index}")
            }
            R.id.btn_goods_buy->{
                any as GoodsRealBinder
                context?.startActivity<GoodsActivity>()
            }
        }
    }

}
fun toast(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}