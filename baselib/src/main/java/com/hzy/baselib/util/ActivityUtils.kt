package com.hzy.baselib.util

import android.app.Activity

import java.util.ArrayList


object ActivityUtils {
    val aliveActivity = ArrayList<Activity>()
    val top: Activity?
        get() = if (aliveActivity.size > 0) {
            aliveActivity[aliveActivity.size - 1]
        } else {
            null
        }

    fun isTop(activity: Activity): Boolean {
        return if (aliveActivity.size > 0 && aliveActivity[aliveActivity.size - 1] === activity) {
            true
        } else {
            false
        }
    }

    fun addActivity(activity: Activity) {
        this.aliveActivity.add(activity)
    }

    fun removeActivity(activity: Activity) {
        this.aliveActivity.remove(activity)
    }

    fun clearActivity() {
        for (activity in aliveActivity) {
            activity?.finish()

        }
        aliveActivity.clear()
    }

    /**
     *
     * @param num 1关掉当前2关掉除去当前一个
     */
    fun closeActivity(num: Int) {
        if (aliveActivity.size >= num) {
            for (i in 0 until num) {
                val activity = aliveActivity[aliveActivity.size - 1]
                if (activity != null) {
                    aliveActivity.remove(activity)
                    activity.finish()
                }
            }
        }
    }

}
