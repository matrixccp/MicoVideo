package com.vue.mv.microvideo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.vue.mv.microvideo.base.BaseFragmentAdapter
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseFragment
import com.vue.mv.microvideo.utils.StatusBarUtil
import com.vue.mv.microvideo.widget.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_recommand.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui
 * @ClassName:      RecommendFragment
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:29
 * @UpdateRemark:
 * @Version:        1.0
 */
class RecommendFragment : BaseFragment() {
    private val tabList = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    companion object {
        fun getInstance(): RecommendFragment {
            val fragment = RecommendFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_recommand

    override fun initView() {

        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }

        tabList.add("关注")
        tabList.add("分类")
        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        /**
         * getSupportFragmentManager() 替换为getChildFragmentManager()
         */
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }

    override fun lazyLoad() {
    }
}