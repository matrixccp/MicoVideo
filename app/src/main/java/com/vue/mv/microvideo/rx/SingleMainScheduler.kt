package com.vue.mv.microvideo.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.rx
 * @ClassName:      SchedulerUtils
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/29 9:50
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class SingleMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())
