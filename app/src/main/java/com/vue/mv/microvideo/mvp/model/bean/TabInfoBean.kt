package com.vue.mv.microvideo.mvp.model.bean

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      TabInfoBean
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
}
