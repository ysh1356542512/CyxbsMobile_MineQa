package com.mredrock.cyxbs.mine.page.stamp.exchange.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mredrock.cyxbs.common.ui.BaseMVPVMActivity
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setOnSingleClickListener
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampGoodsDetailRealBinding
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopCardJumpData
import com.mredrock.cyxbs.mine.page.stamp.config.CenterConfig.SHOP_TO_GOODS_EXTRA
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig.GOODS_PHOTO_ITEM_KEY
import com.mredrock.cyxbs.mine.page.stamp.config.GoodsConfig.GOODS_PHOTO_LIST_KEY
import com.mredrock.cyxbs.mine.page.stamp.exchange.dialog.NoneProductDialog
import com.mredrock.cyxbs.mine.page.stamp.exchange.presenter.GoodsPresenter
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BannerViewPager
import com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel.GoodsViewModel
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew
import com.mredrock.cyxbs.mine.page.stamp.shop.dialog.DoubleCheckDialog


class GoodsActivity :
        BaseMVPVMActivity<GoodsViewModel, MineActivityStampGoodsDetailRealBinding, GoodsPresenter>() {
    //初始化BannerViewPager
    private lateinit var bvpViewPager: BannerViewPager<String>

    //商品id
    private var mId = ""

    /**
     * 布局信息
     */
    override fun getLayoutId(): Int = R.layout.mine_activity_stamp_goods_detail_real

    /**
     * P层信息
     */
    override fun createPresenter(): GoodsPresenter {
        val shop: ShopCardJumpData
        return try {
            shop = intent.getSerializableExtra(SHOP_TO_GOODS_EXTRA) as ShopCardJumpData
            mId = shop.id
            GoodsPresenter(shop.id, shop.money)
        } catch (e: NullPointerException) {
            GoodsPresenter("Null", Int.MIN_VALUE)
        }
    }

    /**
     * 得到数据
     */
    override fun fetch() {
        presenter?.fetch()
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.vm = viewModel
    }

    /**
     * 初始化BVP
     */
    override fun initView() {
        //初始化默认数据
        bvpViewPager = findViewById(R.id.bvp_goods_real)
        presenter?.let {
            //默认值
            it.setDefaultData()
            viewModel.goodsUrls.observe(this) { it1 ->
                it.initBVP(bvpViewPager, lifecycle, it1) { position, _ ->
                    val intent = Intent(this@GoodsActivity, GoodsPagerActivity::class.java)
                    intent.putExtra(GOODS_PHOTO_LIST_KEY, it1.toTypedArray())
                    intent.putExtra(GOODS_PHOTO_ITEM_KEY, position)
                    this@GoodsActivity.startActivityForResult(intent,
                            GoodsConfig.GOODS_SHARE_PHOTO_RESPOND)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GoodsConfig.GOODS_SHARE_PHOTO_RESPOND -> {
                bvpViewPager.setCurrentItem(resultCode, false)
            }
        }
    }

    override fun observeData() {}

    /**
     * 初始化Listener
     */
    override fun initListener() {
        binding?.apply {
            fabCenterBack.setOnSingleClickListener { onBackPressed() }
            btnStampBuy.setOnSingleClickListener {
                presenter?.fetch()
                initDialog(vm)
            }
        }
    }

    /**
     * 初始化Dialog
     */
    private fun initDialog(vm: GoodsViewModel?) {
        vm?.goodsInfo?.value?.apply {
            DoubleCheckDialog.showDialog(supportFragmentManager,
                    "确定要用${price}邮票兑换${title}吗", "取消", "确认") {
                vm.userAccount.value?.let { that ->
                    if (that < price) {
                        //邮票不足
                        NoneProductDialog.showDialog(supportFragmentManager,
                                "诶......邮票不够啊....穷日子真不好过呀QAQ", "确认") {
                            Toast.makeText(this@GoodsActivity,
                                    "要多多赚邮票才能和智蔷哥哥基建哦",
                                    Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        apiServiceNew.buyGoodsRep(mId)
                                .setSchedulers()
                                .doOnSubscribe { }
                                .doOnError {
                                    if(it.message=="Unable to resolve host \"be-dev.redrock.cqupt.edu.cn\": No address associated with hostname"
                                            ||it.message=="HTTP 403 "){
                                        NoneProductDialog.showDialog(supportFragmentManager,
                                                "网络瓦特了 联网后再来兑换吧", "确认") {}
                                    }else {
                                        NoneProductDialog.showDialog(supportFragmentManager,
                                                "阿欧，手慢了！下次再来吧= =", "确认") {
                                            Toast.makeText(this@GoodsActivity,
                                                    "智蔷哥哥今天太累了 下次再来吧",
                                                    Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                                .safeSubscribeBy {
                                    Log.d("sss", "initDialog111:${it.info}+${it.status} ")
                                    //手动减少[狗头]
                                    vm.setUserAccount(that - price)
                                    vm.let {
                                        it.goodsAmount.value?.minus(1)?.let { it1 -> it.setGoodsAmount(it1) }
                                    }
                                    //足够 商品为邮物
                                    if (vm.goodsType.value == "邮物") {
                                        NoneProductDialog.showDialog(supportFragmentManager,
                                                "兑换成功！请在30天内到红岩网校领取哦", "确认") {
                                            Toast.makeText(this@GoodsActivity,
                                                    "尤物智蔷giegie购买成功",
                                                    Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        DoubleCheckDialog.showDialog(supportFragmentManager,
                                                "兑换成功！现在就换掉原来的名片吧！", "再想想", "好的") {
                                            Toast.makeText(this@GoodsActivity,
                                                    "速来网校与智蔷giegie基建",
                                                    Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                    }
                }
            }
        }
    }
}