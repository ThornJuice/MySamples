package com.hzy.wan.test

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
//        GlobalScope.launch {
        // delay(1000)
        //  Thread.sleep(1000)
//        println("world")
        //        }
        //println("hello")
        // Thread.sleep(2000)
//        runBlocking {
//            println("world")
//            launch {
//                println("world2")
//            }
//        }
//        runBlocking {
//            val list = arrayListOf(1,2,3)
//            list.forEach { values ->
//                values.toString()
//            }
//        }
      GlobalScope.launch {
            withContext(Dispatchers.Main){
                println("world2")
            }
        }

    }

}
