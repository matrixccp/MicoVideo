package com.vue.mv.microvideo.net

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.net.exception
 * @ClassName:      BaseResponse
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T)