package com.csy.afv.MVP.model

import com.csy.afv.MVP.model.bean.CategoryBean
import com.csy.afv.data.common.io_main
import com.csy.afv.data.net.RetrofitManager
import io.reactivex.Observable
import kotlin.collections.ArrayList

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/12/2 15:24
 */
class CategoryModel {

    fun loadCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory().io_main()
    }
}