package com.vue.mv.microvideo.mvp.presenter

import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.contract.RankContract
import com.vue.mv.microvideo.mvp.model.RankModel
import com.vue.mv.microvideo.net.exception.ExceptionHandle

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      RankPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    private val rankModel by lazy { RankModel() }


    /**
     *  请求排行榜数据
     */
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
            .subscribe({ issue ->
                mRootView?.apply {
                    dismissLoading()
                    setRankList(issue.itemList)
                }
            }, { throwable ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }
}