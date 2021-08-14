package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import com.mredrock.cyxbs.common.utils.extensions.setFullScreen
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.animation.GalleryTransformer
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig
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
                //                override fun onPageClick(position: Int) {
//
//                }
                override fun onPageClick(position: Int, v: View) {
                    setResult(position)
                    v.transitionName = GoodsConfig.GOODS_SHARE_PHOTO_VALUE
                    onBackPressed()
                }
            })
        }.create(
                intent.getStringArrayExtra(GOODS_PHOTO_LIST_KEY).toList()
//                listOf(
//                        "https://images.unsplash.com/photo-1628087237766-a2129e1ab8c7?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDMwfGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
//                        "https://images.unsplash.com/photo-1628029799784-50d803e64ea0?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDI4fGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
//                        "https://images.unsplash.com/photo-1628254747021-59531f59504b?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE2fGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
//                )
        )

        viewPager2.setCurrentItem(intent.getIntExtra(GOODS_PHOTO_ITEM_KEY, 0), false)
    }
}