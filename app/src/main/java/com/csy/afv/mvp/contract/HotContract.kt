package com.csy.afv.mvp.contract

import com.csy.afv.base.IBasePresenter
import com.csy.afv.base.IBaseView
import com.csy.afv.mvp.model.bean.HotBean

/**
 * @Author:CSY
 * @Date:
 * @Description: 热门契约类，获取一些热门的分类
 */
interface HotContract{
    interface View : IBaseView<Presenter> {
        fun setData(bean : HotBean)
    }
    interface Presenter : IBasePresenter {
        fun requestHotData(strategy: String)
    }
}