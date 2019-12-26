package com.csy.afv.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.csy.afv.R
import com.csy.afv.base.BaseFragment
import com.csy.afv.mvp.contract.HotContract
import com.csy.afv.mvp.model.bean.HotBean
import com.csy.afv.mvp.presenter.HotPresenter
import com.csy.afv.ui.adapter.RankAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * @Author:CSY
 * @Date:
 * @Description: HotFragment中两种排行显示的实际显示碎片
 */
class RankFragment : BaseFragment(), HotContract.View {
    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    override fun getLayoutResources(): Int {
        return R.layout.fragment_rank
    }

    override fun initView() {
        //配置recycleview条目信息
        view!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = context?.let { RankAdapter(it, mList) }!!
        view!!.recyclerView.adapter = mAdapter
        if (arguments != null) {
            //根据不同种类strategy获取不同信息
            mStrategy = arguments!!.getString("strategy")!!
            mPresenter = HotPresenter(context!!, this)
            mPresenter.requestHotData(mStrategy)
        }

    }

    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if(mList.size>0){
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

}