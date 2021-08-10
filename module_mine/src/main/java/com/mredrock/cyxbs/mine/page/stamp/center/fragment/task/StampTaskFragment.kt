package com.mredrock.cyxbs.mine.page.stamp.center.fragment.task

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseMVPVMFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampTaskBinding
import com.mredrock.cyxbs.mine.page.stamp.center.binder.MultiTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.OneTaskBinder
import com.mredrock.cyxbs.mine.page.stamp.center.binder.TitleBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.binder.MultiTypeBinder
import com.mredrock.cyxbs.mine.page.stamp.center.util.adlmrecyclerview.createMultiTypeAdapter
import com.mredrock.cyxbs.mine.page.stamp.center.viewmodel.StampCenterViewModel

class StampTaskFragment :
    BaseMVPVMFragment<StampCenterViewModel, MineFragmentStampTaskBinding, TaskPresenter>() {

    /**
     * 布局文件
     */
    override fun getLayoutId(): Int = R.layout.mine_fragment_stamp_task

    private val mAdapter by lazy {
        binding?.rvTask?.let {
            it.itemAnimator = DefaultItemAnimator().apply {
                addDuration = 1000
            }
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

    //初始化视图。好像有些多🐟了
    override fun initView() {
    }

    //观察所有数据
    override fun observeData() {
        super.observeData()
        observeTasks()
    }

    //丢锅给presenter刷新数据
    override fun fetch() {
        super.fetch()
        presenter?.fetch()
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
     * 创建presenter
     */
    override fun createPresenter(): TaskPresenter = TaskPresenter()

    //跳转到任务界面。
    fun onClicked(view: View, any: Any?) {
    }
}