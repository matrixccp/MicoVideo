package com.vue.mv.microvideo.base

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.base
 * @ClassName:      BaseFragmentAdapter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/02 21:37
 * @UpdateUser:
 * @UpdateDate:     2018/11/06 19:45
 * @UpdateRemark:
 * @Version:        1.0
 */
class BaseFragmentAdapter : FragmentPagerAdapter {

    private var fragmentList: List<Fragment>? = ArrayList()
    private var mTitles: List<String>? = null

    constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>) : super(fm) {
        this.mTitles = mTitles
        setFragments(fm, fragmentList, mTitles)
    }

    //刷新fragment
    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>) {
        this.mTitles = mTitles
        if (this.fragmentList != null) {
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft?.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (null != mTitles) mTitles!![position] else ""
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }

}