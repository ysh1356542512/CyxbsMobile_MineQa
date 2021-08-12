package com.mredrock.cyxbs.mine.page.stamp.exchange.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.exchange.util.BaseBannerAdapter

class PagerBannerAdapter : BaseBannerAdapter<String, PagerBannerAdapter.ViewHolder>() {

    override fun getLayoutId(viewType: Int) = R.layout.mine_item_bvp_small_pager

    override fun onBind(holder: ViewHolder, data: String, position: Int, pageSize: Int) {
        Glide.with(holder.imageView.context).load(data).into(holder.imageView)
//        holder.imageView.setImageResource(data)
    }


    override fun createViewHolder(parent: ViewGroup, itemView: View, viewType: Int): ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_banner_pager)
    }

}