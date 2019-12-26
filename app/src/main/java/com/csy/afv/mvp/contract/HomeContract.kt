package com.csy.afv.mvp.contract

import com.csy.afv.base.IBasePresenter
import com.csy.afv.base.IBaseView
import com.csy.afv.mvp.model.bean.HomeBean

/**
 * @Author:CSY
 * @Date:
 * @Description: 主页契约类
 */
interface HomeContract{
    interface View : IBaseView<Presenter> {
        //把数据传给view界面
        fun setData(bean : HomeBean)
    }
    interface Presenter : IBasePresenter {
        fun requestHomeData()
    }
}