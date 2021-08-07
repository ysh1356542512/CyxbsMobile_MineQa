package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseMVPVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampTaskBinding
import com.mredrock.cyxbs.mine.page.stamp.center.binder.MultiTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.OneTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.TitleBinder
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter

class StampTaskFragment :
    BaseMVPVMFragment< StampTaskViewModel, MineFragmentStampTaskBinding,TaskPresenter>() {

    private val mAdapter by lazy {
        binding?.rvTask?.let {
            createMultiTypeAdapter(it, LinearLayoutManager(context))
        }
    }

    override fun initView() {
        val handler: ClickEventHandler = ClickEventHandler()
        mAdapter?.notifyAdapterChanged(
            mutableListOf<MultiTypeBinder<*>>().apply {
                add(
                    OneTaskBinder(
                        FirstLevelTask("每日签到", "每日签到 +10", false)
                    ).also { it.setOnClickListener(handler::onClicked) }
                )
                add(
                    TitleBinder("更多任务").also { it.setOnClickListener(handler::onClicked) }
                )
                addAll((0..2).map {
                    MultiTaskBinder(
                        MoreTask(
                            "逛邮问",
                            "浏览5条动态 +15",
                            it,
                            false
                        )
                    ).also { it.setOnClickListener(handler::onClicked) }
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

    override fun createPresenter(): TaskPresenter = TaskPresenter()

    inner class ClickEventHandler() {
        fun onClicked(v: View, any: Any?) {

        }
    }

}