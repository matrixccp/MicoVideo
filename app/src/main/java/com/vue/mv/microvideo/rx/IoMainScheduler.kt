package com.vue.mv.microvideo.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.rx
 * @ClassName:      IoMainScheduler
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/28 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
