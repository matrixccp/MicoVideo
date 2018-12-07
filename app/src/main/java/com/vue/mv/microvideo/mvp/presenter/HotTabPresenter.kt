package com.vue.mv.microvideo.mvp.presenter

import com.hazz.kotlinmvp.mvp.contract.HotTabContract
import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.model.HotTabModel
import com.vue.mv.microvideo.net.exception.ExceptionHandle

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      HotTabPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class HotTabPresenter : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }


    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
            .subscribe({ tabInfo ->
                mRootView?.setTabInfo(tabInfo)
            }, { throwable ->
                //处理异常
                mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            })
        addSubscription(disposable)
    }
}