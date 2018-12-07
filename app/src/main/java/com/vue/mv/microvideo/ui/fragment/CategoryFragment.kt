package com.vue.mv.microvideo.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.vue.mv.microvideo.ui.adapter.CategoryAdapter
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseFragment
import com.vue.mv.microvideo.base.showToast
import com.vue.mv.microvideo.mvp.contract.CategoryContract
import com.vue.mv.microvideo.mvp.model.bean.CategoryBean
import com.vue.mv.microvideo.mvp.presenter.CategoryPresenter
import com.vue.mv.microvideo.net.exception.ErrorStatus
import com.vue.mv.microvideo.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_category.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui.activity
 * @ClassName:      CategoryFragment
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/09 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/11 18:43
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {


    private val mPresenter by lazy { CategoryPresenter() }

    private val mAdapter by lazy { activity?.let { CategoryAdapter(it, mCategoryList, R.layout.item_category) } }

    private var mTitle: String? = null
    private var mCategoryList = ArrayList<CategoryBean>()


    /**
     * 伴生对象
     */
    companion object {
        fun getInstance(title: String): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_category


    @Suppress("DEPRECATION")
    override fun initView() {
        mPresenter.attachView(this)


        mLayoutStatusView = multipleStatusView

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!

                outRect.set(
                    if (position % 2 == 0) 0 else offset, offset,
                    if (position % 2 == 0) offset else 0, offset
                )
            }

        })

        //状态栏透明和间距处理
//        StatusBarUtil.darkMode(activity)
//        StatusBarUtil.setPaddingSmart(activity, toolbar)
//        StatusBarUtil.setPaddingSmart(activity,mRecyclerView)

    }

    override fun lazyLoad() {

        //获取分类信息
        mPresenter.getCategoryData()
    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
    }

    /**
     * 显示分类信息
     */
    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter?.setData(mCategoryList)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }


}
