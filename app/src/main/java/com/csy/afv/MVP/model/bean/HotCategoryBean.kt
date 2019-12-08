package com.csy.afv.MVP.model.bean

/**
 * Created by xuekai on 2017/9/4.
 */
data class HotCategoryBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)
    data class Tab(val id: Long, val name: String, val apiUrl: String)
}