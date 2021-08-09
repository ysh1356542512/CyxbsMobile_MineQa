package com.mredrock.cyxbs.mine.page.stamp.center.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealContainerBinder
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.util.RecyclerviewAtVP2
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel


/**
* @Date : 2021/8/8
* @By : ysh
* @Usage : 邮票小店P层
* @Request : God bless my code
*/
class CenterShopPresenter: BasePresenter<StampCenterViewModel>() {

    fun setRecyclerViewContent(recyclerView: RecyclerviewAtVP2,context: Context,fragment: CenterShopFragment){
        val mAdapter = createMultiTypeAdapter(recyclerView,LinearLayoutManager(context))
        mAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            //用map函数给每一个Binder设置监听事件 具体事件在这个类里的onClick函数定义 根据id来判断binder
            add(GoodsRealContainerBinder((1..20)
                    .filter {
                        it>0
                    }.map{
                        GoodsRealBinder(it).apply {
                            setOnClickListener(fragment::onClick)
                        }
                    },"装扮"))
            add(GoodsRealContainerBinder((1..20).map{
                GoodsRealBinder(it).apply {
                    setOnClickListener(fragment::onClick)
                }
            },"邮物"))
        })
    }

    override fun fetch() {

    }

}