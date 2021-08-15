package com.mredrock.cyxbs.mine.page.stamp.exchange.presenter

import android.view.View
import androidx.lifecycle.Lifecycle
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GoodsInfo


class GoodsPresenter(private val goodsId: String, private val money: Int) :
        BasePresenter<GoodsViewModel>(), GoodsContract.GoodsPresenter {

    override fun initBVP(
            bvpViewPager: BannerViewPager<String>,
            lifecycle: Lifecycle,
            list: List<String>,
            func: (Int, View) -> Unit,
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
                override fun onPageClick(position: Int, v: View) {
                    func(position, v)
                }
            }).create(list)
        }
    }

    override fun fetch() {
        apiServiceNew.getGoodsInfo(goodsId)
                .setSchedulers()
                .doOnSubscribe {}
                .doOnError { }
                .safeSubscribeBy(
                        onNext = {
                            vm?.apply {
                                setGoodsValue(it.data)
                                if (it.data.type == 0) {
                                    vm?.apply {
                                        setUserAccount(money)
                                        setGoodsType("邮物")
                                        setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
                                                "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                                        setGoodsDate("永久")
                                        setGoodsUrls(it.data.urls)
                                        setGoodsAmount(it.data.amount)
                                    }
                                } else {
                                    vm?.apply {
                                        setUserAccount(money)
                                        setGoodsType("装饰")
                                        setDescription("1、虚拟商品版权归红岩网校工作站所有。",
                                                "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
                                        setGoodsDate("${it.data.life}天")
                                        setGoodsUrls(it.data.urls)
                                        setGoodsAmount(it.data.amount)
                                    }
                                }
                            }
                        },
                        onError = {
                            BaseApp.context.toast("网络请求失败了呢~")
                        },
                        onComplete = {})
    }

    override fun setDefaultData() {
        vm?.apply {
            setGoodsValue(GoodsInfo.Data(
                    999, "未知", 0, 121, "未知", 1, listOf()
            ))
            setGoodsType("未知")
            setDescription("1、每个实物商品每人限兑换一次，已经兑换的商品不能退货换货也不予折现。",
                    "2、在法律允许的范围内，本活动的最终解释权归红岩网校工作站所有。")
            setGoodsDate("未知")
            setGoodsAmount(999)
            setUserAccount(1222)
        }
    }
}