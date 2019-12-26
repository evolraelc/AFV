package com.csy.afv.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.csy.afv.R
import com.csy.afv.base.BaseFragment
import com.csy.afv.ui.adapter.HotAdatpter
import kotlinx.android.synthetic.main.fragment_hot.view.*

/**
 * @Author:CSY
 * @Date:
 * @Description: “热门”界面的两种类别
 */
class HotFragment : BaseFragment() {
    //两种“热门”类别
    var mTabs = listOf<String>("周排行", "月排行").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>
    //api搜索时需要输入的类别，即Strategy
    val STRATEGY = arrayOf("weekly", "monthly")
    override fun getLayoutResources(): Int {
        return R.layout.fragment_hot
    }

    override fun initView() {
        //判断选择类别，通过viewPager在另一个fragment里面显示出来
        var weekFragment: RankFragment = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy", STRATEGY[0])
        weekFragment.arguments = weekBundle
        var monthFragment: RankFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString("strategy", STRATEGY[1])
        monthFragment.arguments = monthBundle
        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(monthFragment as Fragment)
        view!!.vp_content.adapter = fragmentManager?.let { HotAdatpter(it, mFragments, mTabs) }
        view!!.tabs.setupWithViewPager(view!!.vp_content)
    }

}