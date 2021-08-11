package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampGoodsDetailRealBinding
import com.mredrock.cyxbs.mine.page.stamp.config.CenterConfig.SHOP_TO_GOODS_KEY
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig.GOODS_SHARE_PHOTO_VALUE
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig.SHOP_SHARE_PHOTO_VALUE
import com.mredrock.cyxbs.mine.page.stamp.exchange.presenter.GoodsPresenter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.shop.dialog.DoubleCheckDialog
import com.mredrock.cyxbs.mine.page.stamp.shop.dialog.NoneProductDialog


class GoodsActivity :
        BaseMVPVMActivity<GoodsViewModel, MineActivityStampGoodsDetailRealBinding, GoodsPresenter>() {
    private lateinit var bvpViewPager: BannerViewPager<Int>

    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_goods_detail_real

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.mine_activity_stamp_goods_detail_real)
        binding?.vm = viewModel

        Log.e(TAG, "$viewModel $binding")

    }

    override fun initView() {

//        val bannerViewPager = BannerAdapter()
        bvpViewPager = findViewById(R.id.bvp_goods_real)
        presenter?.let {
            it.initBVP(bvpViewPager, lifecycle) { position, v ->
                val intent = Intent(this@GoodsActivity, GoodsPagerActivity::class.java)
                intent.putExtra(ExchangeConfig.GOODS_PHOTO_ITEM_KEY, position)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@GoodsActivity, v, GOODS_SHARE_PHOTO_VALUE).toBundle()
                this@GoodsActivity.startActivityForResult(intent,
                        ExchangeConfig.GOODS_SHARE_PHOTO_RESPOND,
                        options)
            }
        }
//        bvpViewPager.apply {
//            //设置生命周期 当Activity可视的时候开启自动轮播
//            setLifecycleRegistry(lifecycle)
//            //自动轮询
//            setAutoPlay(true)
//            //循环滚动
//            setCanLoop(true)
//            //设置轮询时间间隔
//            setInterval(2)
//            //显示指示器
//            setCanShowIndicator(true)
//            //设置适配器
//            setAdapter(bannerViewPager)
//            setOnPageClickListener(object : BaseBannerAdapter.OnPageClickListener {
////                override fun onPageClick(position: Int) {
////
////                }
//
//                override fun onPageClick(position: Int, v: View) {
//                    //传入 position 和 List<Photo>
//                    val intent = Intent(this@GoodsActivity,GoodsPagerActivity::class.java)
//                    intent.putExtra(ExchangeConfig.GOODS_PHOTO_ITEM_KEY,position)
//                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@GoodsActivity, v, GOODS_SHARE_PHOTO_VALUE).toBundle()
//                    this@GoodsActivity.startActivityForResult(intent,
//                            ExchangeConfig.GOODS_SHARE_PHOTO_RESPOND,
//                            options)
//                }
//            })
//        }.create(
//            listOf(
//                R.drawable.mine_ic_banner_pic,
//                R.drawable.mine_ic_banner_pic,
//                R.drawable.mine_ic_banner_pic
//            )
//        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ExchangeConfig.GOODS_SHARE_PHOTO_RESPOND -> {
                bvpViewPager.setCurrentItem(resultCode, false)
            }
        }
    }

    override fun initListener() {
        binding?.apply {

            fabCenterBack.setOnSingleClickListener {
                onBackPressed()
            }
            btnStampBuy.transitionName = SHOP_SHARE_PHOTO_VALUE
            //之后可能会对照片进行点击看大图的转换 到时候再说
            btnStampBuy.setOnSingleClickListener {
//                val dialog = NoneProductDialog()
//                        .setContent("啊哦！手慢了！下次再来吧！")
//                        .setPositiveButtonText("2")
//                        .setPositiveButtonClick {
//                            Toast.makeText(this@GoodsActivity, "确认", Toast.LENGTH_SHORT).show()
//                        }
//                dialog.show(supportFragmentManager,"dialog")
//                NoneProductDialog.showDialog {
//                    Toast.makeText(StampApplication.context, "确认", Toast.LENGTH_SHORT).show()
//                }
//                val dialog = DoubleCheckDialog()
//                        .setContent("确认要用100邮票兑换PM名片吗兑换成功!")
//                        .setNegativeButtonText("再想想")
//                        .setPositiveButtonText("好的")
//                        .setNegativeButtonClick {
//                            Toast.makeText(this@GoodsActivity, "我还没悟透", Toast.LENGTH_SHORT).show()
//                        }
//                        .setPositiveButtonClick {
//                            Toast.makeText(this@GoodsActivity, "我想通了", Toast.LENGTH_SHORT).show()
//                        }
//                dialog.show(supportFragmentManager, "dialog")
                vm?.goodsInfo?.value?.apply {
                    DoubleCheckDialog.showDialog(supportFragmentManager,
                            "确定要用${price}邮票兑换${title}吗", "取消", "确认") {
                        val isStampEnough = (0..1).random()
                        if (isStampEnough == 0) {
                            //邮票不足
                            NoneProductDialog.showDialog(supportFragmentManager,
                                    "诶......邮票不够啊....穷日子真不好过呀QAQ", "确认") {
                                Toast.makeText(this@GoodsActivity, "要多多赚邮票才能和智蔷哥哥基建哦", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            var isAmountEnough = (0..1).random()
                            if (isAmountEnough == 0) {
                                isAmountEnough = (0..1).random()
                                //邮票足库存不足
                                NoneProductDialog.showDialog(supportFragmentManager,
                                        "阿欧，手慢了！下次再来吧= =", "确认") {
                                    Toast.makeText(this@GoodsActivity, "智蔷哥哥今天太累了 下次再来吧", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                //足够 商品为邮物
                                if (intent.getIntExtra(SHOP_TO_GOODS_KEY, -1) == 0) {
                                    NoneProductDialog.showDialog(supportFragmentManager,
                                            "兑换成功！请在30天内到红岩网校领取哦", "确认") {
                                        Toast.makeText(this@GoodsActivity, "尤物智蔷giegie购买成功", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    DoubleCheckDialog.showDialog(supportFragmentManager,
                                    "兑换成功！现在就换掉原来的名片吧！","再想想","好的"){
                                        Toast.makeText(this@GoodsActivity, "速来网校与智蔷giegie基建", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    override fun fetch() {
        presenter?.fetch()
    }


    override fun observeData() {

    }

    override fun createPresenter(): GoodsPresenter = GoodsPresenter(intent.getIntExtra(SHOP_TO_GOODS_KEY, -1))


}