package com.mredrock.cyxbs.mine.page.stamp.center.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.animation.ZoomOutPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.GoodsRealContainerBinder
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.StampTaskFragment
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopPageData
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductOne
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductTwo
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopTitle
import com.mredrock.cyxbs.mine.page.stamp.center.util.RecyclerviewAtVP2
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.config.CenterConfig
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter


/**
 * @Date : 2021/8/8
 * @By : ysh
 * @Usage : 邮票小店P层
 * @Request : God bless my code
 */
class CenterShopPresenter : BasePresenter<StampCenterViewModel>(){
    /*fun setRecyclerViewContent(
        recyclerView: RecyclerviewAtVP2,
        context: Context,
        fragment: CenterShopFragment
    ) {
        val mAdapter = createMultiTypeAdapter(recyclerView, LinearLayoutManager(context))
        mAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            //用map函数给每一个Binder设置监听事件 具体事件在这个类里的onClick函数定义 根据id来判断binder
            add(GoodsRealContainerBinder((1..20)
                .filter {
                    it > 0
                }.map {
                    GoodsRealBinder(it).apply {
                        setOnClickListener(fragment::onClick)
                    }
                }, "装扮"
            )
            )

            add(GoodsRealContainerBinder((1..20).map {
                GoodsRealBinder(it).apply {
                    setOnClickListener(fragment::onClick)
                }
            }, "邮物"))
        })
    }*/

    fun mapDoubleCardToOne(indices: IntRange, decorator: List<ShopProductOne>): List<ShopProductTwo> {
        return (indices step 2).filter {
            it < indices.last
        }.map {
            ShopProductTwo(
                decorator[it].image,
                decorator[it].price,
                decorator[it].restCount,
                decorator[it].name,
                decorator[it+1].image,
                decorator[it+1].price,
                decorator[it+1].restCount,
                decorator[it+1].name
            )
        }
    }

    override fun fetch() {

    }

}