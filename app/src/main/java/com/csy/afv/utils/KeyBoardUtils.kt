package com.csy.afv.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 * @Author:CSY
 * @Date:
 * @Description: 软键盘弹出和关闭工具类：在search时使用
 */
object KeyBoardUtils {

    fun openKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun closeKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}