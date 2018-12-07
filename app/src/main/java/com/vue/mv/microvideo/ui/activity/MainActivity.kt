package com.vue.mv.microvideo.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseActivity
import com.vue.mv.microvideo.base.showToast
import com.vue.mv.microvideo.mvp.model.bean.TabEntity
import com.vue.mv.microvideo.ui.fragment.HomeFragment
import com.vue.mv.microvideo.ui.fragment.MineFragment
import com.vue.mv.microvideo.ui.fragment.RecommendFragment
import com.vue.mv.microvideo.ui.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui.activity
 * @ClassName:      MainActivity
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/28 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("首页", "推荐", "搜索", "音乐")

    private var mIndex = 0

    private val mIconUnSelectIds = intArrayOf(
        R.mipmap.ic_home_normal,
        R.mipmap.ic_recommend_normal,
        R.mipmap.ic_search_normal,
        R.mipmap.ic_mine_normal
    )

    private val mIconSelectIds = intArrayOf(
        R.mipmap.ic_home_selected,
        R.mipmap.ic_recommend_selected,
        R.mipmap.ic_search_selected,
        R.mipmap.ic_mine_selected
    )

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mRecommendFragment: RecommendFragment? = null
    private var mSearchFragment: SearchFragment? = null
    private var mMineFragment: MineFragment? = null

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        mTab.currentTab = mIndex
        switchFragment(mIndex)
    }


    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }

        mTab.setTabData(mTabEntities)
        mTab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance().let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mRecommendFragment?.let {
                transaction.show(it)
            } ?: RecommendFragment.getInstance().let {
                mRecommendFragment = it
                transaction.add(R.id.fl_container, it, "discovery")
            }
            2  //热门
            -> mSearchFragment?.let {
                transaction.show(it)
            } ?: SearchFragment.getInstance().let {
                mSearchFragment = it
                transaction.add(R.id.fl_container, it, "hot")
            }
            3 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance().let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }

            else -> {

            }
        }
        mIndex = position
        mTab.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mRecommendFragment?.let { transaction.hide(it) }
        mSearchFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }


    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        if (mTab != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }


    override fun start() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
