package com.csy.afv.mvp.presenter

import android.content.Context
import com.csy.afv.mvp.contract.HomeContract
import com.csy.afv.mvp.model.HomeModel
import com.csy.afv.mvp.model.bean.HomeBean
import com.csy.afv.utils.applySchedulers
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description: 处理数据，主要是负责将从model层获得的主页数据传递给view
 */
class HomePresenter(context: Context,view : HomeContract.View) : HomeContract.Presenter{
    var mContext : Context? = null
    var mView : HomeContract.View? = null
    val mModel : HomeModel by lazy {
        HomeModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestHomeData()
    }

    override fun requestHomeData() {
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,true,"0") }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }
    fun requestMoreData(data: String?){
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,false,data) }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }


}




