package com.vue.mv.microvideo.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.HomeBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      SearchContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface SearchContract {

    interface View : IBaseView {
        /**
         * 设置热门关键词数据
         */
        fun setHotWordData(string: ArrayList<String>)

        /**
         * 设置搜索关键词返回的结果
         */
        fun setSearchResult(issue: HomeBean.Issue)
        /**
         * 关闭软件盘
         */
        fun closeSoftKeyboard()

        /**
         * 设置空 View
         */
        fun setEmptyView()


        fun showError(errorMsg: String,errorCode:Int)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 获取热门关键字的数据
         */
        fun requestHotWordData()

        /**
         * 查询搜索
         */
        fun querySearchData(words:String)

        /**
         * 加载更多
         */
        fun loadMoreData()
    }
}