package com.csy.afv.mvp.contract

import com.csy.afv.base.IBasePresenter
import com.csy.afv.base.IBaseView
import com.csy.afv.mvp.model.bean.FindBean

/**
 * @Author:CSY
 * @Date:
 * @Description: 发现界面契约类
 */
interface FindContract{
    interface View : IBaseView<Presenter> {
        fun setData(beans : MutableList<FindBean>)
    }
    interface Presenter : IBasePresenter {
        fun requestFindData()
    }
}