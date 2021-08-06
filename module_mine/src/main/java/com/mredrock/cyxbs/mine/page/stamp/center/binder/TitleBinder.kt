package com.mredrock.cyxbs.mine.page.stamp.center.binder

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineLayoutTaskTitleBinding
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/6  23:35
 *@signature 我们不明前路，却已在路上
 */
class TitleBinder(val title: String) : MultiTypeBinder<MineLayoutTaskTitleBinding>() {
    override fun layoutId(): Int = R.layout.mine_layout_task_title

    override fun areContentsTheSame(other: Any): Boolean =
        other is TitleBinder && other.title == title

    override fun onBindViewHolder(binding: MineLayoutTaskTitleBinding) {
        binding?.title = title
    }
}