package com.vue.mv.microvideo.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.flexbox.*
import com.vue.mv.microvideo.MyApplication
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseFragment
import com.vue.mv.microvideo.base.showToast
import com.vue.mv.microvideo.mvp.contract.SearchContract
import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.mvp.presenter.SearchPresenter
import com.vue.mv.microvideo.net.exception.ErrorStatus
import com.vue.mv.microvideo.ui.adapter.CategoryDetailAdapter
import com.vue.mv.microvideo.ui.adapter.HotKeywordsAdapter
import com.vue.mv.microvideo.utils.CleanLeakUtils
import kotlinx.android.synthetic.main.fragment_search.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui
 * @ClassName:      SearchFragment
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:29
 * @UpdateRemark:
 * @Version:        1.0
 */
class SearchFragment : BaseFragment(), SearchContract.View {

    override fun closeSoftKeyboard() {

    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    companion object {
        fun getInstance(): SearchFragment {
            val fragment = SearchFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val mPresenter by lazy { SearchPresenter() }

    private val mResultAdapter by lazy {
        context?.let {
            CategoryDetailAdapter(
                it,
                itemList,
                R.layout.item_category_detail
            )
        }
    }

    private var mHotKeywordsAdapter: HotKeywordsAdapter? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private var mTextTypeface: Typeface? = null

    private var keyWords: String? = null

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    override fun initView() {
        mPresenter.attachView(this)
        //细黑简体字体
        mTextTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        tv_title_tip.typeface = mTextTypeface
        tv_hot_search_words.typeface = mTextTypeface
        //初始化查询结果的 RecyclerView
        mRecyclerView_result.layoutManager = LinearLayoutManager(context)
        mRecyclerView_result.adapter = mResultAdapter

        //实现自动加载
        mRecyclerView_result.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView_result.layoutManager.itemCount
                val lastVisibleItem =
                    (mRecyclerView_result.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })

        //键盘的搜索按钮
        et_search_view.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                keyWords = et_search_view.text.toString().trim()
                if (keyWords.isNullOrEmpty()) {
                    showToast("请输入你感兴趣的关键词")
                } else {
                    mPresenter.querySearchData(keyWords!!)
                }
            }
            false
        }

        mLayoutStatusView = multipleStatusView

//        //状态栏透明和间距处理
//        StatusBarUtil.darkMode(activity)
//        StatusBarUtil.setPaddingSmart(context, toolbar)

    }

    override fun lazyLoad() {
        mPresenter.requestHotWordData()
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }

    /**
     * 设置热门关键词
     */
    override fun setHotWordData(string: ArrayList<String>) {
        showHotWordView()
        mHotKeywordsAdapter = context?.let { HotKeywordsAdapter(it, string, R.layout.item_flow_text) }

        val flexBoxLayoutManager = FlexboxLayoutManager(context)
        flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      //按正常方向换行
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW   //主轴为水平方向，起点在左端
        flexBoxLayoutManager.alignItems = AlignItems.CENTER    //定义项目在副轴轴上如何对齐
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START  //多个轴对齐方式

        mRecyclerView_hot.layoutManager = flexBoxLayoutManager
        mRecyclerView_hot.adapter = mHotKeywordsAdapter
        //设置 Tag 的点击事件
        mHotKeywordsAdapter?.setOnTagItemClickListener {
            keyWords = it
            mPresenter.querySearchData(it)
        }
    }

    /**
     * 设置搜索结果
     */
    override fun setSearchResult(issue: HomeBean.Issue) {
        loadingMore = false

        hideHotWordView()
        tv_search_count.visibility = View.VISIBLE
        tv_search_count.text = String.format(resources.getString(R.string.search_result_count), keyWords, issue.total)

        itemList = issue.itemList
        mResultAdapter?.addData(issue.itemList)


    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    /**
     * 没有找到相匹配的内容
     */
    override fun setEmptyView() {
        showToast("抱歉，没有找到相匹配的内容")
        hideHotWordView()
        tv_search_count.visibility = View.GONE
        mLayoutStatusView?.showEmpty()
    }

    /**
     * 隐藏热门关键字的 View
     */
    private fun hideHotWordView() {
        layout_hot_words.visibility = View.GONE
        layout_content_result.visibility = View.VISIBLE
    }

    /**
     * 显示热门关键字的 流式布局
     */
    private fun showHotWordView() {
        layout_hot_words.visibility = View.VISIBLE
        layout_content_result.visibility = View.GONE
    }


    override fun onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(context)
        super.onDestroy()
        mPresenter.detachView()
        mTextTypeface = null
    }
}