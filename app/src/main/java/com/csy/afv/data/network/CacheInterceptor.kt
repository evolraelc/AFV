package com.csy.afv.data.network

import android.content.Context
import com.csy.afv.utils.LogUtil.logE
import com.csy.afv.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.CacheControl

/**
 * @Author:CSY
 * @Date:  2019/11/03 09:12
 * @Description: 设置缓存拦截器
 */
class CacheInterceptor(context: Context) : Interceptor{
    val context = context
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkUtils.isNetConneted(context)) {
            val response = chain.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            val cacheControl = request.toString()
            logE("CacheInterceptor: 6s load cahe" + cacheControl)
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + maxAge)
                .build()
        } else {
            logE("CacheInterceptor: no network load cache")
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
            val response = chain.proceed(request)
            //set cahe times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build()
        }
    }
}
