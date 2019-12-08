package com.csy.afv.ui.views.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/17 19:37
 */
class Indicator(context: Context): View(context) {
    private val paint: Paint

    init {
        paint = Paint()
        paint.isAntiAlias = true
        setState(false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        measuredHeight
        measuredWidth
        canvas!!.translate((measuredWidth / 2).toFloat(), (measuredHeight / 2).toFloat())
        canvas.drawCircle(0f, 0f, (measuredWidth / 2).toFloat(), paint)
    }

    fun setState(selected: Boolean) {
        if (selected) {
            paint.color = 0xffffffff.toInt()
        } else {
            paint.color = 0x88ffffff.toInt()
        }
        invalidate()
    }
}