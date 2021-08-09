package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig
import com.mredrock.cyxbs.mine.page.stamp.exchange.activity.GoodsPagerActivity
import com.mredrock.cyxbs.mine.page.stamp.exchange.activity.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter

class GoodsPresenter: BasePresenter<GoodsViewModel>() {


    fun initBVP(bvpViewPager:BannerViewPager<Int>,lifecycle:Lifecycle,func:(Int,View)->Unit){
        val bannerViewPager = BannerAdapter()
        bvpViewPager.apply {
            //设置生命周期 当Activity可视的时候开启自动轮播
            setLifecycleRegistry(lifecycle)
            //自动轮询
            setAutoPlay(true)
            //循环滚动
            setCanLoop(true)
            //设置轮询时间间隔
            setInterval(2)
            //显示指示器
            setCanShowIndicator(true)
            //设置适配器
            setAdapter(bannerViewPager)
            setOnPageClickListener(object : BaseBannerAdapter.OnPageClickListener {
//                override fun onPageClick(position: Int) {
//
//                }

                override fun onPageClick(position: Int, v: View) {
                    //传入 position 和 List<Photo>
                    func(position,v)
                }
            }).create(
                    listOf(
                            R.drawable.mine_ic_banner_pic,
                            R.drawable.mine_ic_banner_pic,
                            R.drawable.mine_ic_banner_pic
                    )
            )
        }
    }
}