package com.csy.afv.utils

import org.jetbrains.anko.*

/**
 * @author: CSY.
 * @Description: AnkoLogger日志
 * @Date: 2019/12/4 19:59
 */
object LogUtil: AnkoLogger {

    fun logV(str: Any?){
        verbose(str)
    }
    fun logD(str: Any?){
        debug(str)
    }
    fun logI(str: Any?){
        info(str)
    }
    fun logW(str: Any?){
        warn(str)
    }
    fun logE(str: Any?){
        error(str)
    }
}