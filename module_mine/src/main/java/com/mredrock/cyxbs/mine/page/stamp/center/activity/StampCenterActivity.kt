package com.mredrock.cyxbs.mine.page.stamp.center.activity

import android.os.Bundle
import android.transition.Slide
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampCenterBinding
import com.mredrock.cyxbs.mine.page.stamp.detail.activity.StampDetailActivity
import com.mredrock.cyxbs.mine.page.stamp.ext.isFirstTimeComeIn
import kotlinx.android.synthetic.main.mine_activity_stamp_center.*
import java.lang.Exception


/**
 * @Date : 2021/8/4
 * @By : ysh
 * @Usage : 邮票中心主界面
 * @Request : God bless my code
 */
class StampCenterActivity :
        BaseMVPVMActivity<StampCenterViewModel, MineActivityStampCenterBinding, StampCenterPresenter>() {


    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_center

    override fun onCreate(savedInstanceState: Bundle?) {
        window.enterTransition = Slide()
        super.onCreate(savedInstanceState)
        binding?.vm = viewModel
    }

    override fun observeData() {
        super.observeData()
        viewModel.apply {
        }
    }

    override fun initView() {

        //先进行 viewPager2 和 TabLayout 的绑定
        binding?.apply {
            try {
                val field = srlRefresh.javaClass.getDeclaredField("mTouchSlop")
                field.isAccessible = true
                field.set(srlRefresh, 300)
            }catch (e:Exception){
                e.printStackTrace()
            }
            includeCenterPart2.mineCenterPartThree.tvCenterCommend.setTextArray(
                    arrayOf(
                            "你还有待领取的商品，请尽快领取",
                            "每日任务记得要完成哦",
                            "小店里有很多商品快去兑换吧"
                    )
            )
            presenter?.let {
                it.initVP2(
                        this@StampCenterActivity,
                        vpCenter
                ) {
                    TabLayoutMediator(
                            tlCenter,
                            vpCenter,
                            it
                    ).attach()
                }
            }

            presenter?.let { tlCenter.addOnTabSelectedListener(it) }
//            context = this@StampCenterActivity

//            vpCenter.initViewPager2 {
//                it.apply {
//
//                }
//            }
//
//            vpCenter.setPageTransformer(ZoomOutPageTransformer())
//            vpCenter.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            val fragments = arrayListOf<Fragment>(CenterShopFragment(), StampTaskFragment())
//            //设置Adapter
//            vpCenter.adapter = PagerAdapter(fragments, this@StampCenterActivity)
//
//            vpCenter.apply {
//                offscreenPageLimit = 2
//                //此处可动态设置tabItem布局
//                //关于tablayout代码的设计 这样能够尽可能的减少网络请求的次数
//                presenter?.let {
//                    TabLayoutMediator(
//                        tlCenter,
//                        vpCenter,
//                        it
//                    ).attach()
//                }
//            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        presenter?.fetch()
    }

    override fun fetch() {
        presenter?.fetch()
    }

    override fun initListener() {
        binding?.apply {
            fabCenterBack.setOnSingleClickListener {
                onBackPressed()
            }
            includeCenterPart2.mineCenterPartThree.ivCenterDetail.setOnSingleClickListener { startActivity<StampDetailActivity>() }
            includeCenterPart2.mineCenterPartThree.tvCenterDetail.setOnSingleClickListener { startActivity<StampDetailActivity>() }
            //这个到时候可能会跳转至订单详情页 需要在 ExchangeDetailActivity中再加一个方法来跳转到详情页
            includeCenterPart2.mineCenterPartThree.tvCenterCommend.setOnSingleClickListener { startActivity<StampDetailActivity>() }
            srlRefresh.setOnRefreshListener {
                presenter?.refresh(srlRefresh)
            }
        }
    }

    override fun createPresenter(): StampCenterPresenter = StampCenterPresenter(isFirstTimeComeIn())
}