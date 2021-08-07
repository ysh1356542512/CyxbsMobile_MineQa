package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mredrock.cyxbs.common.utils.extensions.setFullScreen
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.GalleryTransformer

/**
* @Date : 2021/8/7
* @By : ysh
* @Usage : 图片具体页
* @Request : God bless my code
*/
class GoodsPagerActivity : AppCompatActivity() {

    private lateinit var viewPager2: BannerViewPager<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_activity_goods_pager)
        setFullScreen()
        viewPager2 = findViewById(R.id.vp_goods_pager)


        val adapter = BannerAdapter()
        viewPager2.apply {
            //设置生命周期
            setLifecycleRegistry(lifecycle)
            //设置显示多个视图的宽度
            setRevealWidth(30)
            //循环滚动
            setCanLoop(true)
            //设置滚动动画
            setPageTransformer(GalleryTransformer())
            //显示指示器
            setCanShowIndicator(true)
            //设置适配器
            setAdapter(adapter)
            //设置监听
            setOnPageClickListener(object : BaseBannerAdapter.OnPageClickListener {
                override fun onPageClick(position: Int) {
                    onBackPressed()
                }
            })
        }.create(
                listOf(
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic,
                        R.drawable.mine_ic_banner_pic
                )
        )
        viewPager2.setCurrentItem(intent.getIntExtra("photo_item",0),false)
    }
}