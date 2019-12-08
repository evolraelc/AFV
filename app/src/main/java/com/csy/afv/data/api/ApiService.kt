package com.csy.afv.data.api

import com.csy.afv.MVP.model.bean.CategoryBean
import com.csy.afv.MVP.model.bean.HomeBean
import com.csy.afv.MVP.model.bean.HotCategoryBean
import com.csy.afv.MVP.model.bean.Issue
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/02 16:24
 */
interface ApiService {
    /**
     * 首页数据
     */
    @GET("v2/feed?&num=1")
    fun getFirstHomeData(@Query("date") date: Long): Observable<HomeBean>

    /**
     * 根据nextpageurl请求数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>

    /**
     * issue里面包了itemlist和nextpageurl
     */
    @GET
    fun getIssue(@Url url: String): Observable<Issue>

    /**
     * 热门的类别
     */
    @GET
    fun getHotCategory(@Url url: String): Observable<HotCategoryBean>


    /**
     * 获取回复
     */
    @GET("v2/replies/video?")
    fun getReply(@Query("videoId") videoId: Long): Observable<Issue>

    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Observable<Issue>

    /**
     * 获取分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

    /**
     * 获取分类下的全部数据
     */
    @GET("v4/categories/videoList")
    fun getCategoryItemList(@Query("id") id: Long): Observable<Issue>
}