package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

//import com.mredrock.cyxbs.mine.network.bean.GoodsInfo
import android.view.View
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.mapOrThrowApiException
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ApiServiceNew

class GoodsPresenter(private val goodsId: Int) : BasePresenter<GoodsViewModel>(), GoodsContract.GoodsPresenter {
    private var a = 0

    override fun initBVP(
            bvpViewPager: BannerViewPager<String>,
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
//                            R.drawable.mine_ic_banner_pic,
//                            R.drawable.mine_ic_banner_pic,
//                            R.drawable.mine_ic_banner_pic
                            "https://images.unsplash.com/photo-1628087237766-a2129e1ab8c7?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDMwfGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
                            "https://images.unsplash.com/photo-1628029799784-50d803e64ea0?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDI4fGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
                            "https://images.unsplash.com/photo-1628254747021-59531f59504b?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE2fGJvOGpRS1RhRTBZfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
                    )
            )
        }
    }

    override fun fetch() {
//        val goodsInfo = getGoodsInfo()
//        vm?.setGoodsValue(goodsInfo)
//        if (goodsInfo.type == 0) {
//            vm?.setGoodsType("尤物")
//            vm?.setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
//                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
//            vm?.setGoodsDate("永久")
//        } else {
//            vm?.setGoodsType("装饰")
//            vm?.setDescription("1、虚拟商品版权归红岩网校工作站所有。",
//                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
//            vm?.setGoodsDate("${goodsInfo.life}天")
//        }
        ApiGenerator.getApiService(ApiServiceNew::class.java)
                .getGoodsInfo(goodsId)
                .mapOrThrowApiException()
                .setSchedulers()
                .doOnSubscribe {

                }
                .doOnError { }
                .safeSubscribeBy {
                    vm?.apply {
                        setGoodsValue(it.data)
                        if (it.data.type == 0) {
                            vm?.setGoodsType("尤物")
                            vm?.setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
                                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                            vm?.setGoodsDate("永久")
                        } else {
                            vm?.setGoodsType("装饰")
                            vm?.setDescription("1、虚拟商品版权归红岩网校工作站所有。",
                                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                            vm?.setGoodsDate("${it.data.life}天")
                        }
                    }
                }
    }

//    private fun getGoodsInfo(): GoodsInfo {
//        when (goodsId) {
//            0 -> return GoodsInfo(1, "精通前后端和移动开发等多项技术", -1, 121, "智蔷哥哥", 0, listOf("1"), 1222)
//            1 -> return GoodsInfo(10, "凭此名片，可来网校找智蔷哥哥基建", 15, 121, "智蔷基建名片", 1, listOf("1"), 1222)
//            else -> {
//
//            }
//        }
//        return GoodsInfo(0, "", 0, 0, "", 0, listOf(), 0)
//    }

//    fun getGoodsInfo():GoodsInfo{
//        ApiGenerator.getApiService(ApiServiceNew::class.java)
//                .getGoodsInfo(goodsId)
//                .mapOrThrowApiException()
//                .setSchedulers()
//                .doOnSubscribe {
//
//                }
//                .doOnError {  }
//                .safeSubscribeBy {
//
//                }
//
//
//
//    }


}