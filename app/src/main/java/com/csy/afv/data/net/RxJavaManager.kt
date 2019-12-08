package com.csy.afv.data.net

import io.reactivex.disposables.Disposable

/**
 * @author: CSY.
 * @Description: RxJava2
 * @Date: 2019/11/07 18:33
 */
interface RxJavaManager {
    fun dispose(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}