package com.vue.mv.microvideo.mvp.model

import com.vue.mv.microvideo.mvp.model.bean.CategoryBean
import com.vue.mv.microvideo.net.RetrofitManager
import com.vue.mv.microvideo.rx.SchedulerUtils
import io.reactivex.Observable

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      CategoryModel
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryModel {


    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
            .compose(SchedulerUtils.ioToMain())
    }
}