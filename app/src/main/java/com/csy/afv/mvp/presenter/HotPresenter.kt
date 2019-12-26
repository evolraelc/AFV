package com.csy.afv.mvp.presenter

import android.content.Context
import com.csy.afv.mvp.contract.HotContract
import com.csy.afv.mvp.model.HotModel
import com.csy.afv.mvp.model.bean.HotBean
import com.csy.afv.utils.applySchedulers
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description: 处理数据，主要是负责将从model层获得的热门类别数据传递给view
 */
class HotPresenter(context: Context,view: HotContract.View) : HotContract.Presenter{


    var mContext : Context? = null
    var mView : HotContract.View? = null
    val mModel : HotModel by lazy {
        HotModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {

    }
    override fun requestHotData(strategy: String) {
        val observable : Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!,strategy) }
        observable?.applySchedulers()?.subscribe { bean : HotBean ->
            mView?.setData(bean)
        }
    }

}