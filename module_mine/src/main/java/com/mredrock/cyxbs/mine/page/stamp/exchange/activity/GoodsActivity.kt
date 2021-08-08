package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.mredrock.cyxbs.common.ui.BaseBindingViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampGoodsDetailRealBinding
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig.GOODS_SHARE_PHOTO_VALUE
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig.SHOP_SHARE_PHOTO_VALUE
import com.mredrock.cyxbs.mine.page.stamp.exchange.adapter.BannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.presenter.GoodsPresenter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.shop.dialog.DoubleCheckDialog


class GoodsActivity :
    BaseMVPVMActivity<GoodsViewModel, MineActivityStampGoodsDetailRealBinding,GoodsPresenter>() {
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
            it.initBVP(bvpViewPager,lifecycle){position,v->
                val intent = Intent(this@GoodsActivity,GoodsPagerActivity::class.java)
                    intent.putExtra(ExchangeConfig.GOODS_PHOTO_ITEM_KEY,position)
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
        when(requestCode){
            ExchangeConfig.GOODS_SHARE_PHOTO_RESPOND->{
                bvpViewPager.setCurrentItem(resultCode,false)
            }
        }
    }

    override fun initListener() {
        binding?.apply {
            ivCenterBack.setOnSingleClickListener {
                btnStampBuy.transitionName = SHOP_SHARE_PHOTO_VALUE
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
                val dialog = DoubleCheckDialog()
                        .setContent("确认要用100邮票兑换PM名片吗兑换成功!")
                        .setNegativeButtonText("再想想")
                        .setPositiveButtonText("好的")
                        .setNegativeButtonClick {
                            Toast.makeText(this@GoodsActivity, "我还没悟透", Toast.LENGTH_SHORT).show()
                        }
                        .setPositiveButtonClick {
                            Toast.makeText(this@GoodsActivity, "我想通了", Toast.LENGTH_SHORT).show()
                        }
                dialog.show(supportFragmentManager,"dialog")
            }


        }
    }


    override fun observeData() {

    }

    override fun createPresenter(): GoodsPresenter = GoodsPresenter()


}