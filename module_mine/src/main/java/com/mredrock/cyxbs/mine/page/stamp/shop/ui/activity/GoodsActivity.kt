package com.mredrock.cyxbs.mine.page.stamp.shop.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.shop.ui.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.shop.util.BannerViewPager

class GoodsActivity : AppCompatActivity() {
    private lateinit var bvpViewPager: BannerViewPager<Int>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.mine_activity_stamp_goods_detail_real)
        val bannerViewPager = BannerAdapter()
        bvpViewPager = findViewById(R.id.bvp_goods_real)
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
        }.create(
                listOf(
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic
                )
        )
    }
}