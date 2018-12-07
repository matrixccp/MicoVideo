package com.vue.mv.microvideo.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.scwang.smartrefresh.header.MaterialHeader
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseFragment
import com.vue.mv.microvideo.base.showToast
import com.vue.mv.microvideo.mvp.contract.HomeContract
import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.mvp.presenter.HomePresenter
import com.vue.mv.microvideo.net.exception.ErrorStatus
import com.vue.mv.microvideo.ui.adapter.HomeAdapter
import com.vue.mv.microvideo.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui
 * @ClassName:      HomeFragment
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:23
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:23
 * @UpdateRemark:
 * @Version:        1.0
 */
class HomeFragment : BaseFragment(), HomeContract.View {

    private val mPresenter by lazy { HomePresenter() }

    private var loadingMore = false

    private var isRefresh = false

    private var num: Int = 1

    private var mHomeAdapter: HomeAdapter? = null
    private var mMaterialHeader: MaterialHeader? = null

    companion object {
        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        mPresenter.attachView(this)
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            mPresenter.requestHomeData(num)
        }
        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        //打开下拉刷新区域块背景:
        mMaterialHeader?.setShowBezierWave(true)
        //设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)


        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = mRecyclerView.childCount
                    val itemCount = mRecyclerView.layoutManager.itemCount
                    val firstVisibleItem =
                        (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true
                            mPresenter.loadMoreData()
                        }
                    }
                }
            }

            //RecyclerView滚动的时候调用
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                } else {
                    if (mHomeAdapter?.mData!!.size > 1) {
                        val itemList = mHomeAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition + mHomeAdapter!!.bannerItemSize - 1]
                    }
                }
            }
        })


        mLayoutStatusView = multipleStatusView

        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }

    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
        // Adapter
        mHomeAdapter = activity?.let { HomeAdapter(it, homeBean.issueList[0].itemList) }
        //设置 banner 大小
        mHomeAdapter?.setBannerSize(homeBean.issueList[0].count)

        mRecyclerView.adapter = mHomeAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore = false
        mHomeAdapter?.addItemData(itemList)
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
}