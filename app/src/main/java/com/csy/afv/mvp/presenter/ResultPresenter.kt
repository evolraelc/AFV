package com.csy.afv.mvp.presenter

import android.content.Context
import com.csy.afv.mvp.contract.ResultContract
import com.csy.afv.mvp.model.ResultModel
import com.csy.afv.mvp.model.bean.HotBean
import com.csy.afv.utils.applySchedulers
import io.reactivex.Observable


/**
 * @Author:CSY
 * @Date:
 * @Description: 处理数据，主要是负责将从model层获得的搜索后的数据传递给view
 */
class ResultPresenter(context: Context, view: ResultContract.View) : ResultContract.Presenter {


    var mContext: Context? = null
    var mView: ResultContract.View? = null
    val mModel: ResultModel by lazy {
        ResultModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestResultData(query: String, start: Int) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, query, start) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}