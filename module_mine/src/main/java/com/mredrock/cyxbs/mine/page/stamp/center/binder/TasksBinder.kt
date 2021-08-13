package com.mredrock.cyxbs.mine.page.stamp.center.binder

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineLayoutTaskTitleBinding
import com.mredrock.cyxbs.mine.databinding.MineTaskRecycleItemMultiProgressBinding
import com.mredrock.cyxbs.mine.databinding.MineTaskRecycleItemOnePregressBinding
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder

/**
 *@author ZhiQiang Tu
 *@time 2021/8/10  10:16
 *@signature 我们不明前路，却已在路上
 */
class MultiTaskBinder(val moreTask: MoreTask) :
        MultiTypeBinder<MineTaskRecycleItemMultiProgressBinding>() {
    override fun layoutId(): Int = R.layout.mine_task_recycle_item_multi_progress

    override fun areContentsTheSame(other: Any): Boolean = false

    @SuppressLint("ObjectAnimatorBinding")
    override fun onBindViewHolder(binding: MineTaskRecycleItemMultiProgressBinding) {
        binding?.data = moreTask
        binding?.handler = this
        binding.mineSpace.setMaxProgress(5f)
        val animator = ObjectAnimator.ofFloat(binding.mineSpace, "currentProgress", 0f, moreTask.progress.toFloat())
        animator.duration = 2000
        animator.start()

    }
}

class OneTaskBinder(val firstLevelTask: FirstLevelTask) :
        MultiTypeBinder<MineTaskRecycleItemOnePregressBinding>() {
    override fun layoutId(): Int = R.layout.mine_task_recycle_item_one_pregress

    override fun areContentsTheSame(other: Any): Boolean = false

    override fun onBindViewHolder(binding: MineTaskRecycleItemOnePregressBinding) {
        binding?.data = firstLevelTask
        binding?.handler = this
    }
}

class TitleBinder(val title: String) : MultiTypeBinder<MineLayoutTaskTitleBinding>() {
    override fun layoutId(): Int = R.layout.mine_layout_task_title

    override fun areContentsTheSame(other: Any): Boolean =
            other is TitleBinder && other.title == title

    override fun onBindViewHolder(binding: MineLayoutTaskTitleBinding) {
        binding?.title = title
    }
}