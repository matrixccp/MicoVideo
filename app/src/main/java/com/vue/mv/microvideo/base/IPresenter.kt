package com.vue.mv.microvideo.base


/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.base
 * @ClassName:      IPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/30 21:37
 * @UpdateUser:
 * @UpdateDate:     2018/11/06 19:45
 * @UpdateRemark:
 * @Version:        1.0
 */
interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
