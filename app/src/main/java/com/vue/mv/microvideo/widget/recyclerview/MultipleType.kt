package com.vue.mv.microvideo.widget.recyclerview

/**
 * desc: 多布局条目类型
 */

interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}
