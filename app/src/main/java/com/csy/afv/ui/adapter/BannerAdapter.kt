package com.csy.afv.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.csy.afv.MVP.model.bean.Item
import com.csy.afv.ui.views.home.HomeBannerItem

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/11 09:04
 */
class BannerAdapter : PagerAdapter() {


    var datas: ArrayList<Item>? = null
    var viewList: ArrayList<HomeBannerItem> = ArrayList()


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        if (datas == null) {
            return 0
        } else {
            return datas!!.size
        }
    }


    override fun destroyItem(container: ViewGroup, position: Int,
                             `object`: Any) {
        container.removeView(viewList[position])
        viewList[position].releasePlayer()
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (viewList.size <= position) {
            val homeBannerItem = HomeBannerItem(container.context, datas!![position])
            viewList.add(homeBannerItem)
        }
        val view = viewList[position]
        container.addView(view)
        viewList[position].play()
//        view.setOnClickListener { v->v.context.toActivityWithSerializable<DetailActivity>(datas!![position]) }
        return view
    }

}