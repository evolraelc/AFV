package com.csy.afv.mvp.contract

import com.csy.afv.base.IBasePresenter
import com.csy.afv.base.IBaseView
import com.csy.afv.mvp.model.bean.HotBean

/**
 * @Author:CSY
 * @Date:
 * @Description: 搜索结果契约类
 */
interface ResultContract {
    interface View : IBaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : IBasePresenter {
        fun requestResultData(query: String, start: Int)
    }
}