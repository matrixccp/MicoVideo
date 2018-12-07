package com.vue.mv.microvideo.mvp.contract

import com.vue.mv.microvideo.base.IBaseView
import com.vue.mv.microvideo.base.IPresenter
import com.vue.mv.microvideo.mvp.model.bean.HomeBean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      VideoDetailContract
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
interface VideoDetailContract {

    interface View : IBaseView {

        /**
         * 设置视频播放源
         */
        fun setVideo(url: String)

        /**
         * 设置视频信息
         */
        fun setVideoInfo(itemInfo: HomeBean.Issue.Item)

        /**
         * 设置背景
         */
        fun setBackground(url: String)

        /**
         * 设置最新相关视频
         */
        fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>)

        /**
         * 设置错误信息
         */
        fun setErrorMsg(errorMsg: String)


    }

    interface Presenter : IPresenter<View> {

        /**
         * 加载视频信息
         */
        fun loadVideoInfo(itemInfo: HomeBean.Issue.Item)

        /**
         * 请求相关的视频数据
         */
        fun requestRelatedVideo(id: Long)

    }


}