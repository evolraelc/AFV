package com.csy.afv.ui.fragment

import com.csy.afv.R
import com.csy.afv.base.BaseFragment
import com.csy.afv.mvp.contract.FindContract
import com.csy.afv.mvp.model.bean.FindBean
import com.csy.afv.mvp.presenter.FindPresenter
import com.csy.afv.ui.adapter.FindAdapter
import kotlinx.android.synthetic.main.fragment_find.view.*

/**
 * @Author:CSY
 * @Date:
 * @Description: “发现”界面，由于时间原因只提供了一些类别显示，并没有进一步的信息显示
 */
class FindFragment : BaseFragment(), FindContract.View {
    var mPresenter: FindPresenter? = null
    var mAdapter : FindAdapter? = null
    var mList : MutableList<FindBean>? = null


    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mList = beans
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
        mPresenter = context?.let { FindPresenter(it,this) }
        mPresenter?.start()
        mAdapter = context?.let { FindAdapter(it,mList) }
        view!!.gv_find.adapter = mAdapter
    }

}