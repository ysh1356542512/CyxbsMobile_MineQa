package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

//import com.mredrock.cyxbs.mine.network.bean.GoodsInfo
import android.view.View
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.shop.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew

//import com.mredrock.cyxbs.mine.page.stamp.network.api.ApiServiceNew

class GoodsPresenter(private val goodsId: String,private val money: Int) : BasePresenter<GoodsViewModel>(), GoodsContract.GoodsPresenter {


    override fun initBVP(
            bvpViewPager: BannerViewPager<String>,
            lifecycle: Lifecycle,
            list: List<String>,
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
                    list
//                    listOf(
////                            R.drawable.mine_ic_banner_pic,
////                            R.drawable.mine_ic_banner_pic,
////                            R.drawable.mine_ic_banner_pic
//
//                    )
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
        apiServiceNew.getGoodsInfo(goodsId)
                .setSchedulers()
                .doOnSubscribe {

                }
                .doOnError { }
                .safeSubscribeBy {
                    vm?.apply {
                        setGoodsValue(it.data)
                        if (it.data.type == 0) {
                            vm?.apply {
                                setGoodsType("邮物")
                                setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
                                        "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                                setGoodsDate("永久")
                                setGoodsUrls(it.data.urls)
                            }

                        } else {
                            vm?.apply {
                                setUserAccount(money)
                                setGoodsType("装饰")
                                setDescription("1、虚拟商品版权归红岩网校工作站所有。",
                                        "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                                setGoodsDate("${it.data.life}天")
                                setGoodsUrls(it.data.urls)
                            }

                        }
                    }
                }

//        ApiGenerator.getApiService(ApiServiceNew::class.java)
//                .getGoodsInfo(goodsId)
//                .mapOrThrowApiException()
//                .setSchedulers()
//                .doOnSubscribe {
//
//                }
//                .doOnError { }
//                .safeSubscribeBy {
//                    vm?.apply {
//                        setGoodsValue(it.data)
//                        if (it.data.type == 0) {
//                            vm?.setGoodsType("尤物")
//                            vm?.setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
//                                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
//                            vm?.setGoodsDate("永久")
//                        } else {
//                            vm?.setGoodsType("装饰")
//                            vm?.setDescription("1、虚拟商品版权归红岩网校工作站所有。",
//                                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
//                            vm?.setGoodsDate("${it.data.life}天")
//                        }
//                    }
//                }
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