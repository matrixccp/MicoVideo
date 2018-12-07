package com.vue.mv.microvideo.mvp.model

import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.net.RetrofitManager
import com.vue.mv.microvideo.rx.SchedulerUtils
import io.reactivex.Observable

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      CategoryDetailModel
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getCategoryDetailList(id)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多数据
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
            .compose(SchedulerUtils.ioToMain())
    }
}