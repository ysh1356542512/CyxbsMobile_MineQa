package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mredrock.cyxbs.common.utils.extensions.setFullScreen
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.animation.GalleryTransformer
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig.GOODS_PHOTO_ITEM_KEY
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig.GOODS_PHOTO_LIST_KEY
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.PagerBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter

/**
 * @Date : 2021/8/7
 * @By : ysh
 * @Usage : 图片具体页
 * @Request : God bless my code
 */
class GoodsPagerActivity : AppCompatActivity() {

    private lateinit var viewPager2: BannerViewPager<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_activity_goods_pager)
        setFullScreen()
        viewPager2 = findViewById(R.id.vp_goods_pager)
        val adapter = PagerBannerAdapter()
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
                override fun onPageClick(position: Int, v: View) {
                    setResult(position)
                    onBackPressed()
                }
            })
        }.create(intent.getStringArrayExtra(GOODS_PHOTO_LIST_KEY).toList())

        viewPager2.setCurrentItem(intent.getIntExtra(GOODS_PHOTO_ITEM_KEY, 0), false)
    }
}