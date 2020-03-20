package com.hzy.smartfield.test

import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.locks.Lock
import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("null") {
        prop, old, new ->
        println(prop)
        println("旧值：$old -> 新值：$new")

    }


    fun <T> method(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    fun foo(bar: Int , block : () -> Int):Int {
        return bar
    }
    fun <T, R> test(receiver: T, block: T.() -> R): R {
        return receiver.block()
    }
}