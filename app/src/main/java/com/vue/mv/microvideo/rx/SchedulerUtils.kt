package com.vue.mv.microvideo.rx

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.rx
 * @ClassName:      SchedulerUtils
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/28 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
