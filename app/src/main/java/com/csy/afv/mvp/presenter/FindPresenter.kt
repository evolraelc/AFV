package com.csy.afv.mvp.presenter

import android.content.Context

import com.csy.afv.mvp.contract.FindContract
import com.csy.afv.mvp.model.FindModel
import com.csy.afv.mvp.model.bean.FindBean
import com.csy.afv.utils.applySchedulers
import io.reactivex.Observable

/**
 * @Author:CSY
 * @Date:
 * @Description:将从model层获得的有关“发现”界面的数据传递给view
 */
class FindPresenter(context: Context, view : FindContract.View) : FindContract.Presenter{
    var mContext : Context? = null
    var mView : FindContract.View? = null
    val mModel : FindModel by lazy {
        FindModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestFindData()
    }

    override fun requestFindData() {
        val observable : Observable<MutableList<FindBean>>? = mContext?.let { mModel.loadData(mContext!!) }
        observable?.applySchedulers()?.subscribe { beans : MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }



}