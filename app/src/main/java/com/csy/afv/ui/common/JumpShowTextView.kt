package com.csy.afv.ui.common

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.csy.afv.data.common.io_main
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author: CSY.
 * @Description: TODO
 * @Date: 2019/11/13 19:33
 */
class JumpShowTextView: FrameLayout {

    val allTime = 700

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    var placeHolder: PercentTextView? = null

    var realTextView: PercentTextView? = null
    var withAnimation=true

    var text: String? = ""
        set(value) {
            reCreateObject()
            init()
            field = value
            placeHolder?.setText(value)
            start()
        }

    var content: String? = ""

    var isBold: Boolean = false
    var color: Int = Color.BLACK
    var singline = false

    var textSize: Float = 52F

    //线程正在运行
    var isRun: Boolean = false

    var marginBottom: Float = 0f


    var subscribe: Disposable? = null


    fun reCreateObject() {
        placeHolder = PercentTextView(context)
        placeHolder?.textSize = textSize
        placeHolder?.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        placeHolder?.visibility = View.INVISIBLE

        realTextView = PercentTextView(context)
        realTextView?.setTextColor(color)
        realTextView?.textSize = textSize
        realTextView?.paint?.setFakeBoldText(isBold)
        realTextView?.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        if (singline) {
            placeHolder?.setSingleLine(singline)
            placeHolder?.ellipsize= TextUtils.TruncateAt.END
            realTextView?.ellipsize= TextUtils.TruncateAt.END
            realTextView?.setSingleLine(singline)
        }

    }

    private fun init() {
//        setBackgroundColor(0xff00ff00.toInt())
        removeAllViews()
        addView(placeHolder)
        addView(realTextView)
    }


    fun start() {
        if (withAnimation) {
            if (isRun) {
                subscribe?.dispose()
            }
            content = ""

            isRun = true
            text?.let {

                if (it.length > 0) {
                    val intervalTime = allTime / it.length
                    subscribe = Observable.interval(intervalTime.toLong(), TimeUnit.MILLISECONDS)
                        .take(it.length.toLong())
                        .io_main()
                        .subscribe({ i ->
                            content = content + it[i.toInt()]
                            realTextView?.setText(content)
                        }, { e -> e.printStackTrace() }, { isRun = false }
                        )
                }

            }
        }else{
            realTextView?.setText(text)
        }



    }

}