package com.csy.afv.MVP.model.bean

data class Issue(
    var count: Int,
    val date: Long,
    val itemList: ArrayList<Item>,
    val publishTime: Long,
    val releaseTime: Long,
    val type: String,
    val nextPageUrl: String,
    val total:Int
)