package com.mredrock.cyxbs.mine.page.stamp.center.fragment

import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mredrock.cyxbs.common.ui.BaseMVPVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentCenterShopBinding
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.binder.*
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop.CenterShopPresenter
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopPageData
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.callback.OnViewClickListener
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.config.CenterConfig.SHOP_TO_GOODS_KEY
import com.mredrock.cyxbs.mine.page.stamp.config.ExchangeConfig
import com.mredrock.cyxbs.mine.page.stamp.exchange.activity.GoodsActivity

class CenterShopFragment :
        BaseMVPVMFragment<StampCenterViewModel, MineFragmentCenterShopBinding, CenterShopPresenter>(),
        OnViewClickListener {
    //初始化adapter
    private val mAdapter by lazy {
        binding?.rvShopReal?.let {
            it.layoutAnimation = AnimationUtils
                    .loadLayoutAnimation(requireContext(),
                            R.anim.mine_shop_rv_layout_animation)

            createMultiTypeAdapter(it, LinearLayoutManager(context))
        }
    }

    //基础配置
    override fun getLayoutId(): Int = R.layout.mine_fragment_center_shop
    override fun createPresenter(): CenterShopPresenter = CenterShopPresenter()

    //初始化视图
    override fun initView() {
        binding?.apply {
            //设置类似key的东西 判断binding是否已经绑定
            setVariable(BR.viewModel, this)
            //这里更新了数据 所以在这里会调用bindingAdapter这类方法 这类参数需要为可空值且要在内部判空处理
            executePendingBindings()
        }
    }

    //观察界面数据
    override fun observeData() {
        super.observeData()
        shardViewModel?.apply {
            //观察邮票小店页面的数据变化
            observeShopData(shopPageData)
        }
    }

    //观察邮票小店的RV数据
    private fun observeShopData(shopPageData: LiveData<ShopPageData>) {
        shopPageData.observe(this) {
            val list = mutableListOf<MultiTypeBinder<*>>().apply {
                //添加第一个title
                add(GoodsTitleBinder(it.title1).apply {/*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                //添加对应内容
                presenter?.mapDoubleCardToOne(it.decorator.indices, it.decorator)?.filter {
                    if (it.restCount != -1 && it.restCount2 != -1) {
                        add(GoodsProductTwoBinder(it).apply { /*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                        true
                    } else {
                        add(GoodsProductOneBinder(it).apply {  /*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                        false
                    }
                }
                //添加第二个title
                add(GoodsTitleBinder(it.title2).apply {/*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                //添加对应内容
                presenter?.mapDoubleCardToOne(it.entity.indices, it.entity)?.filter {
                    if (it.restCount != -1 && it.restCount2 != -1) {
                        add(GoodsProductTwoBinder(it).apply {/*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                        true
                    } else {
                        add(GoodsProductOneBinder(it).apply {/*设置点击监听*/setOnClickListener(this@CenterShopFragment::onClick) })
                        false
                    }
                }
            }
            //设置adapter
            mAdapter?.notifyAdapterChanged(list)
        }
    }

    //点击处理
    override fun onClick(view: View, any: Any?) {
        when (view.id) {
            R.id.goods_container_2, R.id.goods_container_1, R.id.goods_container -> {
                //商品的id
                any as String

                toast(view, "点击${any}")
            }
            R.id.btn_goods_buy_2, R.id.btn_goods_buy_1, R.id.btn_goods_buy -> {
                //商品的id
                any as String
                val intent = Intent(requireActivity(), GoodsActivity::class.java)
                val randoms = (0..1).random()
                if (randoms == 0) {
                    intent.putExtra(SHOP_TO_GOODS_KEY, 0)
                } else {
                    intent.putExtra(SHOP_TO_GOODS_KEY, 1)
                }

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        view,
                        ExchangeConfig.SHOP_SHARE_PHOTO_VALUE
                ).toBundle()
                context?.startActivity(intent, options)
            }
        }
    }
}

fun toast(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}


/*fun setRecyclerViewContent(
       recyclerView: RecyclerviewAtVP2,
       context: Context,
       fragment: CenterShopFragment
   ) {
       val mAdapter = createMultiTypeAdapter(recyclerView, LinearLayoutManager(context))
       mAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
           //用map函数给每一个Binder设置监听事件 具体事件在这个类里的onClick函数定义 根据id来判断binder
           add(GoodsRealContainerBinder((1..20)
               .filter {
                   it > 0
               }.map {
                   GoodsRealBinder(it).apply {
                       setOnClickListener(fragment::onClick)
                   }
               }, "装扮"
           )
           )
           add(GoodsRealContainerBinder((1..20).map {
               GoodsRealBinder(it).apply {
                   setOnClickListener(fragment::onClick)
               }
           }, "邮物"))
       })
   }*/

//    private fun setRecyclerViewContent(){
//        //在这里可以add很多个类型的ContainerBinder
//        mAdapter?.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
//            //用map函数给每一个Binder设置监听事件 具体事件在这个类里的onClick函数定义 根据id来判断binder
//            add(GoodsRealContainerBinder((1..20)
//                    .filter {
//                        it>0
//                    }.map{
//                        GoodsRealBinder(it).apply {
//                setOnClickListener(this@CenterShopFragment::onClick)
//            }
//            },"装扮"))
//            add(GoodsRealContainerBinder((1..20).map{
//                GoodsRealBinder(it).apply {
//                setOnClickListener(this@CenterShopFragment::onClick)
//            }
//            },"邮物"))
//        })
//    }
//设置内容
/*binding?.rvShopReal?.let {
    context?.let { it1 ->
        presenter?.setRecyclerViewContent(
            it,
            it1,
            this
        )
    }
}*/
//        setRecyclerViewContent()