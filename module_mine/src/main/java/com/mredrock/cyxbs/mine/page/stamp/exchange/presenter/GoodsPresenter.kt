package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

import android.view.View
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel

class GoodsPresenter(private val goodsId: Int) : BasePresenter<GoodsViewModel>(), GoodsContract.GoodsPresenter {


    override fun initBVP(
            bvpViewPager: BannerViewPager<Int>,
            lifecycle: Lifecycle,
            func: (Int, View) -> Unit
    ) {
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
                    func(position, v)
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

    override fun fetch() {
        val goodsInfo = getGoodsInfo()
        vm?.setGoodsValue(goodsInfo)
        if (goodsInfo.type == 0) {
            vm?.setGoodsType("邮物")
            vm?.setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
            vm?.setGoodsDate("永久")
        } else {
            vm?.setGoodsType("装饰")
            vm?.setDescription("1、虚拟商品版权归红岩网校工作站所有。",
                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
            vm?.setGoodsDate("${goodsInfo.life}天")
        }
    }

    private fun getGoodsInfo(): GoodsInfo {
        when (goodsId) {
            0 -> return GoodsInfo(99, "规格:10000mAh 放电:5V 2.1A", -1, 121, "小米充电宝", 0, listOf("1"), 1222)
            1 -> return GoodsInfo(99, "带上这个名片，你就是这条街最亮的仔", 15, 121, "掌游PM名片", 1, listOf("1"), 1222)
            else -> {

            }
        }
        return GoodsInfo(0, "", 0, 0, "", 0, listOf(), 0)
    }


}