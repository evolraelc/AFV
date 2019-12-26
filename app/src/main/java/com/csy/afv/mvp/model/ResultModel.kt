package com.csy.afv.mvp.model

import android.content.Context
import com.csy.afv.data.api.ApiService
import com.csy.afv.data.network.RetrofitManager
import com.csy.afv.mvp.model.bean.HotBean
import io.reactivex.Observable

class ResultModel {
    fun loadData(context: Context, query: String, start: Int): Observable<HotBean>? {
        val retrofitClient = RetrofitManager.getInstance(context)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getSearchData(10, query, start)

    }
}
