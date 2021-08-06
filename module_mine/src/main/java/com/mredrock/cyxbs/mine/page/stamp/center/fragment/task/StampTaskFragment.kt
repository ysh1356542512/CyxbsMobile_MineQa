package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseBindingSharedVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampTaskBinding
import com.mredrock.cyxbs.mine.page.stamp.center.binder.MultiTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.OneTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter

class StampTaskFragment :
    BaseBindingSharedVMFragment<StampTaskViewModel, MineFragmentStampTaskBinding>() {

    private val mAdapter by lazy {
        binding?.rvTask?.let {
            createMultiTypeAdapter(it, LinearLayoutManager(context))
        }
    }

    override fun initView() {
        mAdapter?.notifyAdapterChanged(
            mutableListOf<MultiTypeBinder<*>>().apply {
                add(
                    OneTaskBinder(
                        FirstLevelTask("每日签到", "每日签到 +10", false)
                    )
                )
                addAll((0..2).map {
                    MultiTaskBinder(MoreTask("逛邮问", "浏览5条动态 +15", it, false))
                })
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding?.root
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment_stamp_task

    override fun getActivityVMClass(): Class<StampTaskViewModel> = StampTaskViewModel::class.java

}