package com.hzy.smartfield.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.hzy.smartfield.utils.XLog
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        var instance: Context by Delegates.notNull()
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        XLog.isDebug = true
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}