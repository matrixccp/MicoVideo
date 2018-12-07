package com.vue.mv.microvideo.mvp.model

import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.net.RetrofitManager
import com.vue.mv.microvideo.rx.SchedulerUtils
import io.reactivex.Observable

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      SearchModel
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class VideoDetailModel {

    fun requestRelatedData(id: Long): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getRelatedData(id)
            .compose(SchedulerUtils.ioToMain())
    }

}