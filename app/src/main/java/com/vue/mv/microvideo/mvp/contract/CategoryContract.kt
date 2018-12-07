package com.vue.mv.microvideo.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.CategoryBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      CategoryContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }
}