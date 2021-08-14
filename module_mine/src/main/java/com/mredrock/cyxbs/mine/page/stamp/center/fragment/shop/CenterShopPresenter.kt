package com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop

import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductOne
import com.mredrock.cyxbs.mine.page.stamp.center.model.ShopProductTwo


/**
 * @Date : 2021/8/8
 * @By : ysh
 * @Usage : 邮票小店P层
 * @Request : God bless my code
 */
class CenterShopPresenter : BasePresenter<StampCenterViewModel>() {

    //将两个Card Map成一个Card
    fun mapDoubleCardToOne(
        indices: IntRange,
        decorator: List<ShopProductOne>,
    ): List<ShopProductTwo> {
        val list = (indices step 2).filter {
            it < indices.last
        }.map {
            ShopProductTwo(
                decorator[it].image,
                decorator[it].price,
                decorator[it].restCount,
                decorator[it].name,
                decorator[it + 1].image,
                decorator[it + 1].price,
                decorator[it + 1].restCount,
                decorator[it + 1].name,
                decorator[it].id,
                decorator[it + 1].id
            )
        }.toMutableList()
        val maxValue = indices.last
        if ((indices.last + 1) % 2 != 0) {
            list.add(
                ShopProductTwo(
                    decorator[maxValue].image,
                    decorator[maxValue].price,
                    decorator[maxValue].restCount,
                    decorator[maxValue].name,
                    "",
                    Int.MIN_VALUE,
                    Int.MIN_VALUE,
                    "",
                    "-999",
                    "-9999"
                )
            )
        }
        return list
    }

    override fun fetch() {

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

}