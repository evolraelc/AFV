package com.csy.afv.data.net

import android.util.Log
import com.csy.afv.data.api.ApiService
import com.csy.afv.data.api.ConstantUrl.BASE_URL
import com.csy.afv.util.LogUtil.logD
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: CSY.
 * @Description: 配置Retrofit
 * @Date: 2019/11/02 16:45
 */
object RetrofitManager {
    private val retrofit: Retrofit
    private val DEFAULT_TIMEOUT = 5L
    private val okHttpClient: OkHttpClient
    init {
        val logging = Interceptor { chain ->
            val request = chain.request()
            logD("okHttp"+request.url().toString())
            chain.proceed(request)
        }
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    val service: ApiService by lazy { retrofit.create(ApiService::class.java) }

}