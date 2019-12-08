package com.csy.afv.ui.views.home

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/17 20:03
 */
class HomeTextHeaderItem: FrameLayout {

    val textView by lazy {
        TextView(context)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        textView.gravity = Gravity.CENTER
        textView.textSize = 20f
        textView.typeface = Typeface.createFromAsset(context?.assets, "fonts/Lobster-1.4.otf")
        val paint = textView.paint
        paint.setFakeBoldText(true)
        textView.setTextColor(Color.BLACK)
        addView(textView)
    }


    fun setHeaderText(text: String?) {
        textView.setText(text)
    }
}