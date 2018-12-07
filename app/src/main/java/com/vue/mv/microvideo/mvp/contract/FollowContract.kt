package com.vue.mv.microvideo.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.HomeBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      FollowContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface FollowContract {

    interface View : IBaseView {
        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 获取List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }
}