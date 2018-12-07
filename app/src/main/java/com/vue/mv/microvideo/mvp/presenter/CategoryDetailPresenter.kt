package com.vue.mv.microvideo.mvp.presenter

import com.vue.mv.microvideo.mvp.model.CategoryDetailModel
import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.contract.CategoryDetailContract

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      CategoryDetailPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(), CategoryDetailContract.Presenter {

    private val categoryDetailModel by lazy {
        CategoryDetailModel()
    }

    private var nextPageUrl: String? = null

    /**
     * 获取分类详情的列表信息
     */
    override fun getCategoryDetailList(id: Long) {
        checkViewAttached()
        val disposable = categoryDetailModel.getCategoryDetailList(id)
            .subscribe({ issue ->
                mRootView?.apply {
                    nextPageUrl = issue.nextPageUrl
                    setCateDetailList(issue.itemList)
                }
            }, { throwable ->
                mRootView?.apply {
                    showError(throwable.toString())
                }
            })

        addSubscription(disposable)
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            categoryDetailModel.loadMoreData(it)
                .subscribe({ issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setCateDetailList(issue.itemList)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(throwable.toString())
                    }
                })
        }

        disposable?.let { addSubscription(it) }
    }
}