package com.csy.afv.ui.fragment

import android.content.Intent
import android.graphics.Typeface
import android.view.View
import com.csy.afv.R
import com.csy.afv.base.BaseFragment
import com.csy.afv.ui.activity.CacheActivity
import com.csy.afv.ui.activity.WatchActivity
import kotlinx.android.synthetic.main.fragment_mine.view.*

/**
 * @Author:CSY
 * @Date:
 * @Description: “我的”界面，显示信息较少，主要包括“我的缓存”、“观看记录”等信息
 */
class MineFragment : BaseFragment(),View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_watch ->{
                var intent = Intent(activity, WatchActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_advise ->{

            }
            R.id.tv_save ->{
                var intent = Intent(activity, CacheActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_mine
    }

    //设置组件字体
    override fun initView() {
        view!!.tv_advise.setOnClickListener(this)
        view!!.tv_watch.setOnClickListener(this)
        view!!.tv_save.setOnClickListener(this)
        view!!.tv_advise.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        view!!.tv_watch.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        view!!.tv_save.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

}