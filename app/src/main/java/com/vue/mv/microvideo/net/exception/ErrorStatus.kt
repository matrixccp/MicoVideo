package com.vue.mv.microvideo.net.exception

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.net.exception
 * @ClassName:      ErrorStatus
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/10/31 12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/01 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
object ErrorStatus {
    /**
     * 响应成功
     */
    @JvmField
    val SUCCESS = 0

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005

}