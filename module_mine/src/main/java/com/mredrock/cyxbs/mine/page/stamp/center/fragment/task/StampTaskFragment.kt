package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.mredrock.cyxbs.common.config.MINE_CHECK_IN
import com.mredrock.cyxbs.common.config.QA_ENTRY
import com.mredrock.cyxbs.common.ui.BaseMVPVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampTaskBinding
import com.mredrock.cyxbs.mine.page.edit.EditInfoActivity
import com.mredrock.cyxbs.mine.page.stamp.center.activity.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.center.binder.MultiTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.OneTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.TitleBinder
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop.toast
import com.mredrock.cyxbs.mine.page.stamp.center.model.FirstLevelTask
import com.mredrock.cyxbs.mine.page.stamp.center.model.MoreTask
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter

class StampTaskFragment :
        BaseMVPVMFragment<StampCenterViewModel, MineFragmentStampTaskBinding, TaskPresenter>() {
    /**
     * 成员变量放在最前面
     */
    private val mAdapter by lazy {
        binding?.rvTask?.let {
            it.layoutAnimation = AnimationUtils.loadLayoutAnimation(
                    requireContext(),
                    R.anim.mine_task_rv_layout_animation
            )
            /*LayoutAnimationController(
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.mine_task_rv_load_animation
        )
    )*/
            createMultiTypeAdapter(it, LinearLayoutManager(context))
        }
    }

    /**
     * 布局文件
     */
    override fun getLayoutId(): Int = R.layout.mine_fragment_stamp_task

    /**
     * 创建presenter
     */
    override fun createPresenter(): TaskPresenter = TaskPresenter()

    /**
     * 丢锅给presenter刷新数据
     */
    override fun fetch() {
        super.fetch()
        presenter?.fetch()
    }

    /**
     * 初始化视图。好像有些多🐟了
     */
    override fun initView() {
    }

    /**
     * 观察所有数据
     */
    override fun observeData() {
        super.observeData()
        observeTasks()
    }


    /**
     * @init  R.id.rv_task
     * @data viewModel.tasks
     */
    private fun observeTasks() {
        shardViewModel?.tasks?.observe(this) {
            val binders = mutableListOf<MultiTypeBinder<*>>().apply {
                //获取并添加第一类数据
                it.task1.forEach {
                    add(OneTaskBinder(it).also { it.setOnClickListener(::onClicked) })
                }

                //获取并添加第二类数据
                add(
                        TitleBinder(it.title).also { it.setOnClickListener(::onClicked) }
                )

                //获取并添加第三类数据
                it.task2.forEach {
                    add(MultiTaskBinder(it).also { it.setOnClickListener(::onClicked) })
                }
            }

            mAdapter?.notifyAdapterChanged(binders)
        }
    }

    /**
     * 跳转到任务界面。
     */
    private fun onClicked(view: View, any: Any?) {
        var data1: MoreTask? = null
        var data2: FirstLevelTask? = null
        var currentProgress: Int = 0
        var str: String = ""
        var maxProgress = 0
        try {
            data1 = any as MoreTask
            maxProgress = data1.max
            currentProgress = data1.progress
            str = data1.taskName
        } catch (e: ClassCastException) {
            data2 = any as FirstLevelTask
            maxProgress = data2.max
            currentProgress = data2.progress
            str = data2.taskName
        }
        toast(view, str)
        if (maxProgress != 0 && maxProgress != currentProgress) {
            when (str) {
                "逛逛邮问" -> {
                    activity?.finish()
                    val fragment = ARouter.getInstance().build(QA_ENTRY).navigation()
                    Log.e(TAG, "$fragment")
                    activity?.also { startActivity(Intent(it, EditInfoActivity::class.java)) }
                }
                "每日打卡3" -> {
                    ARouter.getInstance().build(MINE_CHECK_IN).navigation()
                }
                "拍案叫绝" -> {
                }
                "完善个人信息" -> {
                    activity?.also { startActivity(Intent(it, EditInfoActivity::class.java)) }
                }
                "绑定志愿者账号" -> {
                }
            }
        }
    }
}