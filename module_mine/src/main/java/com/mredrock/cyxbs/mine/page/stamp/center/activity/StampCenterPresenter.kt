package com.mredrock.cyxbs.mine.page.stamp.center.activity

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.center.animation.ZoomOutPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.shop.CenterShopFragment
import com.mredrock.cyxbs.mine.page.stamp.center.fragment.task.StampTaskFragment
import com.mredrock.cyxbs.mine.page.stamp.center.model.*
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.ext.addFirstOrLast
import com.mredrock.cyxbs.mine.page.stamp.ext.putDate
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew
import com.mredrock.cyxbs.mine.page.stamp.network.bean.ceter.CenterInfo

/**
 *@author ZhiQiang Tu
 *@time 2021/8/7  13:51
 *@signature 我们不明前路，却已在路上
 */
private const val TAG = "StampCenterPresenter"

class StampCenterPresenter(private val isFirstTimeComeIn: Boolean) :
        BasePresenter<StampCenterViewModel>(),
        StampCenterContract.CenterPresenter {

    /**
     *  进行网络请求和vm数据的初始化
     */
    override fun fetch() {
        //进行网络请求
        apiServiceNew.getCenterInfo()
                //.mapOrThrowApiException()
                .setSchedulers()
                .doOnSubscribe { }
                .doOnError { }
                .safeSubscribeBy(
                        onError = {
                            BaseApp.context.toast("网络请求失败了呢~ ${it.message}")
                            Log.e(TAG, "fetch: erro $it")
                        },
                        onComplete = {},
                        onNext = {
                            Log.e(TAG, "fetch: success $it")
                            //设置邮票数目
                            vm?.setUserAccount(it.data.userAmount)
                            //设置时候显示跑马灯
                            vm?.setHasGoodsToGet(it.data.unGotGood)
                            //设置邮票小店的数据
                            val shopPageData = convertToShopData(it)
                            vm?.setShopPageDataValue(shopPageData)
                            //设置邮票任务的数据
                            val taskData = convertToTaskData(it)
                            vm?.setTasksValue(taskData)
                        }
                )
    }

    /**
     *  ViewPager与TabLayout的联动部分
     */
    //TabLayoutMediator.TabConfigurationStrategy
    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        vm?.isClickedToday = !isFirstTimeComeIn
        when (position) {
            0 -> {
                //此处setCustomView来设置布局 tab.setCustomView(R.layout.xxx)
                //在TabLayoutMediator源码的populateTabsFromPagerAdapter方法中可查看逻辑
                tab.setCustomView(R.layout.mine_item_tab_shop)
            }
            else -> {
                //邮票任务在此处进行网络申请 若该日未点击过 则设置带有小红点的布局(在之前统一申请两个fragment和该activity的所有数据)
                //因为每次加载该Activity的时候都要经过此代码 为了减少onTabSelected处网络申请的次数
                //定义一个boolean类型的成员变量isClickToday来供判断 若此处返回的结果表示用户今日已点击过 则为true
                //得到网络申请 若为今日未点击 加载布局 isClickToday = true 若已点击 加载另一个布局isClickToday = false
                if (vm?.isClickedToday == false) {
                    tab.setCustomView(R.layout.mine_item_tab_task_no_click)
                    tab.view.alpha = 0.6f
                } else {
                    tab.setCustomView(R.layout.mine_item_tab_click)
                    tab.view.alpha = 0.6f
                }
            }
        }
    }

    /**
     *  TAB的监听事件
     */
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        tab?.view?.alpha = 0.6f
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val isClickToday = vm?.isClickedToday ?: true
        //先判断isClickToday是否为true 若为true 只切换 为false 则Post提交数据 表示今日第一次点击 并将小红点GONE或者重新换布局
        when (!isClickToday) {
            true -> {
                when (tab?.position) {
                    1 -> {
                        //tab.orCreateBadge
                        //在这个点卡了比较久 因为发现如果只setCustomView一次其实 tab 不会去更换布局 而是会类似于解绑布局 导致切换tab直接不显示布局内容
                        //所以在这里调用两次 setCustomView 一次用来解除布局绑定 第二次来重新绑定来实现切换布局
                        tab.setCustomView(R.layout.mine_item_tab_click)
                        tab.setCustomView(R.layout.mine_item_tab_click)
                        tab.view.alpha = 1.0f
                        vm?.isClickedToday = false
                        //在这里POST数据 并将isClickToday为true表示已经点击
                        putDate()
                    }
                    else -> {
                        tab?.view?.alpha = 1.0f
                    }
                }
            }
            else -> {
                tab?.view?.alpha = 1.0f
            }
        }
    }

    /**
     *  初始化VP2
     */
    override fun initVP2(fgActivity: FragmentActivity, vpCenter: ViewPager2, func: () -> Unit) {
        vpCenter.apply {
            setPageTransformer(ZoomOutPageTransformer())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            val fragments = arrayListOf<Fragment>(CenterShopFragment(), StampTaskFragment())
            adapter = PagerAdapter(fragments, fgActivity)
            func()
        }
    }

    /**
     *  在网络请求失败时的默认数据
     */
    //获取默认数据
    override fun setDefaultPageData() {
        //设置跑马灯
        vm?.setHasGoodsToGet(true)
        //设置User的邮票余额
        vm?.setUserAccount(11000)
        //由于优先展示邮票小店，优先设置邮票小店的默认数据
        val shopData = getShopPageData()
        vm?.setShopPageDataValue(shopData)
        //设置邮票任务的默认数据
        val taskData = getTaskPageData()
        vm?.setTasksValue(taskData)
    }

    /**
     *  刷新数据
     */
    override fun refresh(srlRefresh: SwipeRefreshLayout) {
        apiServiceNew.getCenterInfo()
                .setSchedulers()
                .doOnSubscribe { }
                .doOnError { }
                .safeSubscribeBy(
                        onError = {
                            BaseApp.context.toast("刷新失败")
                            srlRefresh.isRefreshing = false
                        },
                        onComplete = {
                            BaseApp.context.toast("刷新成功")
                        },
                        onNext = {
                            //设置邮票数目
                            vm?.setUserAccount(it.data.userAmount)
                            //设置时候显示跑马灯
                            vm?.setHasGoodsToGet(it.data.unGotGood)
                            //设置邮票小店的数据
                            val shopPageData = convertToShopData(it)
                            vm?.setShopPageDataValue(shopPageData)
                            //设置邮票任务的数据
                            val taskData = convertToTaskData(it)
                            vm?.setTasksValue(taskData)
                            srlRefresh.isRefreshing = false
                        }
                )
    }


    /**
     * 以下全为由数据库数据转化为vm层需要的数据的过程
     */
    private fun convertToTaskData(centerInfo: CenterInfo): StampTaskData {
        val baseTasks: MutableList<FirstLevelTask> = mutableListOf()
        val moreTasks: MutableList<MoreTask> = mutableListOf()
        for (task in centerInfo.data.task) {
            if (task.type == "base") {
                baseTasks.addFirstOrLast(task.currentProgress < task.maxProgress,
                        FirstLevelTask(
                                task.title,
                                task.description,
                                task.currentProgress,
                                task.maxProgress
                        ))
            } else {
                moreTasks.addFirstOrLast(task.currentProgress < task.maxProgress,
                        MoreTask(
                                task.title,
                                task.description,
                                task.currentProgress,
                                task.maxProgress
                        ))
            }
        }
        return StampTaskData(baseTasks, getTitle(), moreTasks)
    }

    private fun convertToShopData(centerInfo: CenterInfo): ShopPageData {
        val decorator = mutableListOf<ShopProductOne>()
        val entity = mutableListOf<ShopProductOne>()
        for (shop in centerInfo.data.shop) {
            if (shop.type == 1) {
                decorator.add(
                        ShopProductOne(
                                shop.url,
                                shop.price,
                                shop.amount,
                                shop.title,
                                shop.id.toString()
                        )
                )
            } else {
                entity.add(
                        ShopProductOne(
                                shop.url,
                                shop.price,
                                shop.amount,
                                shop.title,
                                shop.id.toString()
                        )
                )
            }
        }
        return ShopPageData(
                getShopTitle1(),
                decorator,
                getShopTitle2(),
                entity
        )
    }

    //邮票小店默认数据
    private fun getShopPageData(): ShopPageData {
        val title1 = getShopTitle1()
        val decorator = getShopList()
        val title2 = getShopTitle2()
        val entity = getShopList()
        return ShopPageData(
                title1, decorator, title2, entity
        )
    }

    private fun getShopList(): List<ShopProductOne> {
        return (0..1).map {
            ShopProductOne(
                    "NULL", 121, 20, "挂件挂件挂件挂件挂件", "NULL"
            )
        }
    }

    private fun getShopTitle2(): ShopTitle = ShopTitle("邮物", "请在个人资料中查看")

    private fun getShopTitle1(): ShopTitle = ShopTitle("装扮", "请在个人资料中查看")

    //邮票任务默认数据
    private fun getTaskPageData(): StampTaskData {
        //获取Task1
        val task1 = getTask1()
        //获取Title
        val title = getTitle()
        //获取Task2
        val task2 = getTask2()
        return StampTaskData(task1, title, task2)
    }

    private fun getTitle(): String = "更多任务"

    private fun getTask1(): MutableList<FirstLevelTask> {
        return mutableListOf(
                FirstLevelTask("每日打卡", "每日签到 +10", 0, 1),
                FirstLevelTask("逛逛邮问", "游览5条动态 +15", 1, 5),
                FirstLevelTask("逛逛邮问", "游览5条动态 +15", 5, 5),
        )
    }

    private fun getTask2(): MutableList<MoreTask> {
        return mutableListOf(
                MoreTask("逛逛邮问", "浏览5条动态 +15", 1, 5)
        )
    }

}
