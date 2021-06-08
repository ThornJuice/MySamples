package com.hzy.wan.util

import com.ju.baselibrary.utils.XLog


/**
 * @author: wxj
 * @date: 2021/6/7
 * @description:
 */
class LaunchRecord {
    companion object {
        private var sStart: Long = 0
        fun startRecord() {
            sStart = System.currentTimeMillis()
        }
        fun endRecord() {
            endRecord("")
        }
        fun endRecord(postion: String) {
            val cost = System.currentTimeMillis() - sStart
            XLog.e("Launch...","===$postion===$cost"+"毫秒")
        }
    }
}
