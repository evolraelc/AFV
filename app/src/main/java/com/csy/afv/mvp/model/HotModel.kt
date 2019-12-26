package com.csy.afv.mvp.model

import android.content.Context
import com.csy.afv.data.api.ApiService
import com.csy.afv.data.network.RetrofitManager
import com.csy.afv.mvp.model.bean.HotBean
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description: 获取热门排行数据
 */
class HotModel{
    fun loadData(context: Context, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitManager.getInstance(context)
        val apiService  = retrofitClient.create(ApiService::class.java)
          return apiService?.getHotData(10, strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)

        }
    }