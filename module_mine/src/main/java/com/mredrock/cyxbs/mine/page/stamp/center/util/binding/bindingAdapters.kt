package com.mredrock.cyxbs.mine.page.stamp.center.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop.CenterShopFragment

/**
 * @Date : 2021/8/6
 * @By : ysh
 * @Usage : 对bindingAdapter的事件管理
 * @Request : God bless my code
 */


//加载图片
@BindingAdapter("goodsImage")
fun setImage(Iv: ImageView, goodsImage: String?) {
    if (goodsImage != null) {
        Glide.with(Iv.context).load("https:${goodsImage}").into(Iv)
    } else {
        // TODO: 2021/7/19 显示默认的图
    }
}

//本来想
@BindingAdapter("adapter")
fun bindAdapter(vp: ViewPager2, activity: FragmentActivity) {
    vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    val fragments = arrayListOf<Fragment>(CenterShopFragment(), CenterShopFragment())
    vp.adapter = object : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 2
        }


        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}
