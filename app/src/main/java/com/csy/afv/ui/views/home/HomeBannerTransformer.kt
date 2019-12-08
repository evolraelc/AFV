package com.csy.afv.ui.views.home


import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/17 19:46
 */
class HomeBannerTransformer: ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width: Int = page.width
        page.scrollX =(position * width).toInt()/ 4 * 3
    }
}