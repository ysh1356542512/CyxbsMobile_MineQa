package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampGoodsDetailRealBinding
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager

class GoodsActivity :  BaseBindingViewModelActivity<GoodsViewModel,MineActivityStampGoodsDetailRealBinding>() {
    private lateinit var bvpViewPager: BannerViewPager<Int>

    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_goods_detail_real

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.mine_activity_stamp_goods_detail_real)
        binding?.vm = viewModel

        Log.e(TAG, "$viewModel $binding" )
    }

    override fun initView() {
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

    override fun initListener() {
        binding?.apply {
            ivCenterBack.setOnSingleClickListener {
                onBackPressed()
            }
            //之后可能会对照片进行点击看大图的转换 到时候再说
            btnStampBuy.setOnSingleClickListener {
                //
            }

        }
    }

    override fun observeData() {

    }


}