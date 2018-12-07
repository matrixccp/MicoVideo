package com.vue.mv.microvideo.mvp.model.bean

import java.io.Serializable

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      CategoryBean
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
data class CategoryBean(
    val id: Long,
    val name: String,
    val description: String,
    val bgPicture: String,
    val bgColor: String,
    val headerImage: String
) : Serializable
