package com.vue.mv.microvideo.mvp.presenter

import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.contract.FollowContract
import com.vue.mv.microvideo.mvp.model.FollowModel
import com.vue.mv.microvideo.net.exception.ExceptionHandle

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      FollowPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl: String? = null

    /**
     *  请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = followModel.requestFollowList()
            .subscribe({ issue ->
                mRootView?.apply {
                    dismissLoading()
                    nextPageUrl = issue.nextPageUrl
                    setFollowInfo(issue)
                }
            }, { throwable ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                .subscribe({ issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }

                }, { t ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })


        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}