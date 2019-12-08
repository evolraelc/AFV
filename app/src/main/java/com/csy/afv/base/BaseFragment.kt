package com.csy.afv.base

import androidx.fragment.app.Fragment
import com.csy.afv.R
import com.csy.afv.data.net.RxJavaManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/07 18:20
 */

//Tab栏，默认当前为home界面
val tabsId = listOf(R.id.rb_home,R.id.rb_category, R.id.rb_hot,R.id.rb_mine)
var curFragment = R.id.rb_home

abstract class BaseFragment (id: Int): Fragment(),RxJavaManager{

    var tabId = 0
    init {
        this.tabId=id
    }

    protected val disposables= CompositeDisposable()

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun dispose(disposable: Disposable) {
        disposables.remove(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    open fun setToolBar(): Boolean{
        if(tabId!= curFragment){
            return true
        }
        return false
    }
}