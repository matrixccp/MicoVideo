package com.vue.mv.microvideo.mvp.presenter

import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.contract.SearchContract
import com.vue.mv.microvideo.mvp.model.SearchModel
import com.vue.mv.microvideo.net.exception.ExceptionHandle

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      SearchPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    private var nextPageUrl: String? = null

    private val searchModel by lazy { SearchModel() }


    /**
     * 获取热门关键词
     */
    override fun requestHotWordData() {
        checkViewAttached()
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(
            disposable = searchModel.requestHotWordData()
                .subscribe({ string ->
                    mRootView?.apply {
                        setHotWordData(string)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        )
    }

    /**
     * 查询关键词
     */
    override fun querySearchData(words: String) {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(
            disposable = searchModel.getSearchResult(words)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        } else
                            setEmptyView()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        )

    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        checkViewAttached()
        nextPageUrl?.let {
            addSubscription(
                disposable = searchModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    })
            )
        }

    }


}