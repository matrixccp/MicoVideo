package com.vue.mv.microvideo.mvp.model

import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.net.RetrofitManager
import com.vue.mv.microvideo.rx.SchedulerUtils
import io.reactivex.Observable

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      FollowModel
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class FollowModel {

    /**
     * 获取关注信息
     */
    fun requestFollowList(): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getFollowInfo()
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
            .compose(SchedulerUtils.ioToMain())
    }


}
