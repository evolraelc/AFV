package com.csy.afv.mvp.model

import android.content.Context
import com.csy.afv.data.api.ApiService
import com.csy.afv.data.network.RetrofitManager
import com.csy.afv.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description: 获取主页数据和下一页数据
 */
class HomeModel{
    fun loadData(context: Context,isFirst: Boolean,data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitManager.getInstance(context)
        val apiService  = retrofitClient.create(ApiService::class.java)
        when(isFirst) {
            //通过传入参数判断获取的页码数
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}