package com.csy.afv.data.api

import com.csy.afv.mvp.model.bean.FindBean
import com.csy.afv.mvp.model.bean.HomeBean
import com.csy.afv.mvp.model.bean.HotBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/02 16:24
 */
interface ApiService {
    //获取首页第一页数据
    @GET("v2/feed?num=2")
    fun getHomeData(): Observable<HomeBean>


    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date :String, @Query("num") num :String) : Observable<HomeBean>

    //获取发现频道信息
    @GET("v2/categories")
    fun getFindData() : Observable<MutableList<FindBean>>


    //获取热门排行信息
    @GET("v3/ranklist")
    fun getHotData(@Query("num") num :Int, @Query("strategy") strategy :String,
                   @Query("udid") udid :String, @Query("vc") vc :Int) : Observable<HotBean>

    //获取关键词搜索相关信息
    @GET("v1/search")
    fun getSearchData(@Query("num") num :Int, @Query("query") query :String,
                      @Query("start") start :Int) : Observable<HotBean>
}