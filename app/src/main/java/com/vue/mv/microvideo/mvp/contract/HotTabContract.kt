package com.hazz.kotlinmvp.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.TabInfoBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      HotTabContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface HotTabContract {

    interface View : IBaseView {
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg: String, errorCode: Int)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }
}