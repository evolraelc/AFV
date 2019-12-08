package com.csy.afv.MVP.model

import com.csy.afv.MVP.model.bean.HomeBean
import com.csy.afv.data.common.io_main
import com.csy.afv.data.net.RetrofitManager
import io.reactivex.Observable

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/03 16:49
 */
class HomeModel {

    fun loadFirstData(): Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(System.currentTimeMillis()).io_main()
    }


    fun loadMoreData(url: String): Observable<HomeBean> {
        return RetrofitManager.service.getMoreHomeData(url).io_main()
    }
}