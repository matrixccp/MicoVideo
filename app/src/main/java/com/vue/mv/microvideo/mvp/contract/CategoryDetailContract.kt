package com.vue.mv.microvideo.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.HomeBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      CategoryDetailContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface CategoryDetailContract {

    interface View : IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String)


    }

    interface Presenter : IPresenter<View> {

        fun getCategoryDetailList(id: Long)

        fun loadMoreData()
    }
}