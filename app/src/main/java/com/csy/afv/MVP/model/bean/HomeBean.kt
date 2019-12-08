package com.csy.afv.MVP.model.bean

data class HomeBean(
    val dialog: Any,
    var issueList: ArrayList<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
)