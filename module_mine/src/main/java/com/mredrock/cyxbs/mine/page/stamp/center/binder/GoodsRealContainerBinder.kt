package com.mredrock.cyxbs.mine.page.stamp.center.binder

import androidx.recyclerview.widget.GridLayoutManager
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineItemStampGoodsBinding
import com.mredrock.cyxbs.mine.databinding.MineItemStampGoodsRealContainerBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.GridLayoutDecorationDivider
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.invoke

class GoodsRealContainerBinder(val goods: List<GoodsRealBinder>):MultiTypeBinder<MineItemStampGoodsRealContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.mine_item_stamp_goods_real_container
    }

    override fun areContentsTheSame(other: Any): Boolean = other is GoodsRealContainerBinder &&other.goods ==goods

    override fun onBindViewHolder(binding: MineItemStampGoodsRealContainerBinding) {
        binding.rvGoodsContainer.addItemDecoration(GridLayoutDecorationDivider(binding.root.context, 2, 10))
        (createMultiTypeAdapter(binding.rvGoodsContainer, GridLayoutManager(binding.root.context, 2))) {
            notifyAdapterChanged(goods)
        }
    }
}
class GoodsRealBinder(val index:Int):MultiTypeBinder<MineItemStampGoodsBinding>(){
    override fun layoutId(): Int {
        return R.layout.mine_item_stamp_goods
    }

    override fun areContentsTheSame(other: Any): Boolean = other is GoodsRealBinder && other.index == index
}
