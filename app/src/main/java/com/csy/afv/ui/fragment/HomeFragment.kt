package com.csy.afv.ui.fragment


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csy.afv.R
import com.csy.afv.base.BaseFragment
import com.csy.afv.mvp.contract.HomeContract
import com.csy.afv.mvp.model.bean.HomeBean
import com.csy.afv.mvp.presenter.HomePresenter
import com.csy.afv.ui.adapter.HomeAdatper
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import java.util.regex.Pattern

/**
 * @Author:CSY
 * @Date:
 * @Description: “主页”的fragment，主要完成对recycleView的条目渲染
 */
class HomeFragment : BaseFragment(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {
    var mIsRefresh: Boolean = false
    var mPresenter: HomePresenter? = null
    var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
    var mAdapter: HomeAdatper? = null
    var data: String? = null
    override fun setData(bean: HomeBean) {
        // java.util.regex包负责对字符序列进行正则表达式匹配
        // Pattern负责编译
        // Matcher负责匹配
        //目的是为了筛选数据
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh = false
            view!!.refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }

        }
        //通过高级函数将bean进行筛选，得到视频列表
        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()
    }


    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }


    //初始化函数，在里面对recycleView进行绑定
    override fun initView() {
        mPresenter = context?.let { HomePresenter(it, this) }
        mPresenter?.start()

        //设置recycleView
        view!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = context?.let { HomeAdatper(it, mList) }
        view!!.recyclerView.adapter = mAdapter
        view!!.refreshLayout.setOnRefreshListener(this)
        view!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //刷新操作
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    if (data != null) {
                        mPresenter?.requestMoreData(data)
                    }

                }
            }
        })

    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }
}