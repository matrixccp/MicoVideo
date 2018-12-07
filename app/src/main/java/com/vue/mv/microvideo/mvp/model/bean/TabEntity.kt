package com.vue.mv.microvideo.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity



/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp.contract
 * @ClassName:      TabEntity
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/03  12:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/03 18:20
 * @UpdateRemark:
 * @Version:        1.0
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}