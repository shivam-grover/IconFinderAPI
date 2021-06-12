package com.kakcho.iconfinder.Network

interface ResponseCallback<T> {
    fun success(t: T)
    fun failure(t: T)
}