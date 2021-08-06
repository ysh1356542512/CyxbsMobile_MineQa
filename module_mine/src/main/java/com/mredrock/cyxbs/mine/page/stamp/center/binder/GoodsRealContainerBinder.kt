package com.mredrock.cyxbs.mine.page.stamp.center.binder

import androidx.recyclerview.widget.GridLayoutManager
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineItemStampGoodsBinding
import com.mredrock.cyxbs.mine.databinding.MineItemStampGoodsRealContainerBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.GridLayoutDecorationDivider
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.invoke

/**
* @Date : 2021/8/6
* @By : ysh
* @Usage :
* @Request : God bless my code
*/
//传入了GoodsRealBinder的集合  这个container有两种类型
//1.存放一种类型所有item的集合 布局是一个rv PS：外面不能包含其他布局 否则滑不动
//2.存放标题类Binder 即装饰和邮物两个标题
class GoodsRealContainerBinder(val goods: List<GoodsRealBinder>,val type:String):MultiTypeBinder<MineItemStampGoodsRealContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.mine_item_stamp_goods_real_container
    }

    //判断是否重复
    override fun areContentsTheSame(other: Any): Boolean = other is GoodsRealContainerBinder &&other.goods ==goods

    override fun onBindViewHolder(binding: MineItemStampGoodsRealContainerBinding) {
        binding.rvGoodsContainer.addItemDecoration(GridLayoutDecorationDivider(binding.root.context, 2, 10))
        binding.tvTypeReal.text = type
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
