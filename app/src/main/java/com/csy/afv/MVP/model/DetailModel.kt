package com.csy.afv.MVP.model

import com.csy.afv.MVP.model.bean.Issue
import com.csy.afv.data.common.io_main
import com.csy.afv.data.net.RetrofitManager
import io.reactivex.Observable

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/18 13:21
 */
class DetailModel {
    fun loadRelatedData(id:Long): Observable<Issue> {
        return RetrofitManager.service.getRelatedData(id).io_main()
    }

    fun loadDetailMoreRelatedList(url:String): Observable<Issue> {
        return RetrofitManager.service.getIssue(url).io_main()
    }

    fun loadReplyList(videoId:Long): Observable<Issue> {
        return RetrofitManager.service.getReply(videoId).io_main()
    }
    fun loadMoreReplyList(url:String): Observable<Issue> {
        return RetrofitManager.service.getIssue(url).io_main()
    }
}