package com.mredrock.cyxbs.mine.page.stamp.ui.activity

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.ui.fragment.CenterShopFragment
import kotlinx.android.synthetic.main.mine_activity_stamp_center.*

/**
* @Date : 2021/8/4
* @Author : ysh
* @Request : God bless my code
*/
class StampCenterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_activity_stamp_center)
        //先使用kotlin拓展库得到控件 之后会改为dataBinding
        //先进行 viewPager2 和 tablayout 的绑定
        vp_center.orientation =ViewPager2.ORIENTATION_HORIZONTAL
        val fragments = arrayListOf<Fragment>(CenterShopFragment(),CenterShopFragment())
        vp_center.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 2
            }
            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        vp_center.apply {
            offscreenPageLimit = 2
            //此处可动态设置tabItem布局
            TabLayoutMediator(tl_center,vp_center, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position){
                    0->{
                        tab.text = "邮票小店"
                        //此处setCustomView来设置布局 tab.setCustomView(R.layout.xxx)
                        //在TabLayoutMediator源码的populateTabsFromPagerAdapter方法中可查看逻辑
                    }
                    else->{
                        tab.text = "邮票任务"
                        //邮票任务在此处进行网络申请 若该日未点击过 则设置带有小红点的布局(在之前统一申请两个fragment和该activity的所有数据)
                        //因为每次加载该Activity的时候都要经过此代码 为了减少onTabSelected处网络申请的次数
                        //定义一个boolean类型的成员变量isClickToday来供判断 若此处返回的结果表示用户今日已点击过 则为true
                    }
                }
            }).attach()
        }
        tl_center.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //先判断isClickToday是否为true 若为true 只切换 为false 则Post提交数据 表示今日第一次点击 并将小红点GONE或者重新换布局

            }

        })
    }
}