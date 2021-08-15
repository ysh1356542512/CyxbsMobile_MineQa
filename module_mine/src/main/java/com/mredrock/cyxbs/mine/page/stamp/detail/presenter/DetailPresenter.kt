package com.mredrock.cyxbs.mine.page.stamp.detail.presenter

//import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
//import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo
import android.animation.ObjectAnimator
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.presenter.BasePresenter
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.mine.page.stamp.center.animation.DepthPageTransformer
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.ExchangeRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.fragment.GainRecordFragment
import com.mredrock.cyxbs.mine.page.stamp.detail.util.adapter.PagerAdapter
import com.mredrock.cyxbs.mine.page.stamp.detail.viewmodel.StampDetailViewModel
import com.mredrock.cyxbs.mine.page.stamp.ext.addFirstOrLast
import com.mredrock.cyxbs.mine.page.stamp.network.api.apiServiceNew
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.ExchangeItemInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.detail.GainItemInfo

/**
 * @Date : 2021/8/13
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class DetailPresenter : BasePresenter<StampDetailViewModel>(), StampDetailActivityContract.IPresenter {


    /**
     * 把数据给vm
     */
    override fun fetch() {
        vm?.apply {
            getExchangeData {
                setExchangeListDataValue(it)
            }
            getGainData {
                setGainListDataValue(it)
            }
        }
    }

    /**
     * 获取默认数据
     */
    override fun getDefaultData() {
        vm?.apply {
            getDefaultExchangeData {
                setExchangeListDataValue(it)
            }
            getDefaultGainData {
                setGainListDataValue(it)
            }
        }
    }


    /**
     * 获取默认数据具体方法
     */
    //获取“获取记录”界面的数据
    private fun getDefaultGainData(func: (GainInfo) -> Unit) {
        func(GainInfo(listOf(GainItemInfo(40, 1617339294, "游览任务")), "Loading", -1))
    }

    //获取”兑换记录“的数据
    private fun getDefaultExchangeData(func: (ExchangeInfo) -> Unit) {
        func(
                ExchangeInfo(
                        listOf(
                                ExchangeItemInfo(1617339294, 245769, 4300, true, "卷卷鼠标垫"),
                                ExchangeItemInfo(1617339294, 245769, 4300, false, "卷卷鼠标垫"),
                                ExchangeItemInfo(1617339294, 245769, 4300, true, "卷卷鼠标垫")
                        ), "Loading", -1))

    }

    /**
     * 请求兑换记录数据
     */
    override fun getExchangeData(func: (ExchangeInfo) -> Unit) {
        apiServiceNew.getExchangeInfo()
                .setSchedulers()
                .doOnSubscribe { }
                .doOnError { }
                .safeSubscribeBy {
                    val list: MutableList<ExchangeItemInfo> = mutableListOf()
                    it.data.forEach { it2 -> list.addFirstOrLast(!it2.getOrNot, it2) }
                    val new = ExchangeInfo(list, it.info, it.status)
                    func(new)
                }
    }

    /**
     * 请求明细记录数据
     */
    override fun getGainData(func: (GainInfo) -> Unit) {
        apiServiceNew.getGainInfo(1, 10)
                .setSchedulers()
                .doOnSubscribe { }
                .doOnError { }
                .safeSubscribeBy { func(it) }
    }

    /**
     * 初始化vp
     */
    override fun initViewPagerAndTabs(fgActivity: FragmentActivity, vpDetail: ViewPager2, func: () -> Unit) {
        vpDetail.apply {
            setPageTransformer(DepthPageTransformer())
            adapter = PagerAdapter(listOf(ExchangeRecordFragment(), GainRecordFragment()), fgActivity)
            func()
        }
    }

    /**
     * 初始化TabLayoutMediator
     */
    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        when (position) {
            0 -> {
                tab.text = "兑换记录"
                tab.view.scaleX = 1.12f
                tab.view.scaleY = 1.12f
            }
            1 -> {
                tab.text = "获取记录"
            }
        }
    }

    /**
     * 初始化tabLayout
     */
    override fun onTabSelected(tab: TabLayout.Tab?) {
        val animatorX = ObjectAnimator.ofFloat(tab?.view, "scaleX", 1f, 1.12f)
        val animatorY = ObjectAnimator.ofFloat(tab?.view, "scaleY", 1f, 1.12f)
        animatorX.duration = 800
        animatorY.duration = 800
        animatorX.start()
        animatorY.start()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        val animatorX = ObjectAnimator.ofFloat(tab?.view, "scaleX", 1.12f, 1f)
        val animatorY = ObjectAnimator.ofFloat(tab?.view, "scaleY", 1.12f, 1f)
        animatorX.duration = 800
        animatorY.duration = 800
        animatorX.start()
        animatorY.start()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}