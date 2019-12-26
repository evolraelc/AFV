package com.csy.afv.mvp.model

import android.content.Context
import com.csy.afv.data.api.ApiService
import com.csy.afv.data.network.RetrofitManager
import com.csy.afv.mvp.model.bean.FindBean
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description: 通过api的关键词搜索筛选出想要的数据
 */
class FindModel() {
    fun loadData(context: Context): Observable<MutableList<FindBean>>? {
        val retrofitClient = RetrofitManager.getInstance(context)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData()
    }
}