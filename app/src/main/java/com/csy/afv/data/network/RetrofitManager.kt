package com.csy.afv.data.network

import android.content.Context
import com.csy.afv.data.api.ConstantUrl.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: CSY.
 * @Description: 配置Retrofit
 * @Date: 2019/11/02 16:45
 */
class RetrofitManager private constructor(context: Context){
    private var cache: Cache?=null
    private val mContext: Context=context
    private var retrofit: Retrofit?=null
    private val DEFAULT_TIMEOUT = 5L
    private val okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache)
            .addInterceptor(CacheInterceptor(context))
            .addNetworkInterceptor(CacheInterceptor(context))
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    //封装一个函数用于生成RetrofitManager
    companion object{
        @Volatile
        var instance: RetrofitManager? = null

        fun getInstance(context: Context) : RetrofitManager {
            if (instance == null) {
                synchronized(RetrofitManager::class) {
                    if (instance == null) {
                        instance = RetrofitManager(context)
                    }
                }
            }
            return instance!!
        }

    }
    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null")
        }
        return retrofit?.create(service)
    }

}