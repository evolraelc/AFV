package com.csy.afv.MVP.contract

import com.csy.afv.base.IBasePresenter
import com.csy.afv.base.IBaseView
import com.csy.afv.MVP.model.bean.HomeBean
import com.csy.afv.MVP.model.bean.Item

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/03 17:07
 */
interface HomeContract {

    interface IView: IBaseView<IPresenter>{
        fun setFirstData(homeBean: HomeBean)
        fun setMoreData(itemList: ArrayList<Item>)
        fun onError()
    }

    interface IPresenter: IBasePresenter{
        fun requestFirstData()
        fun requestMoreData()
    }
}