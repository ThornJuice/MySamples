package com.hzy.smartfield.test.threadlocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hzy.smartfield.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()

    }
    fun init(){

        //定义两个MockThreadLocal
        val mtl1 = MockThreadLocal<String>()
        val mtl2 = object : MockThreadLocal<Boolean>() {
            override fun initialValue(): Boolean? {
                return false
            }
        }

//测试按钮点击时执行
        btn.setOnClickListener {
            val thread1 = MockThread(Runnable {
                val name1 = Thread.currentThread().name

                //mtl1未设置值
                Log.e("Main2Activity","$name1 mtl1未设置值时：mtl1.get()=${mtl1.get()}")

                //mtl1设置值：二娃_
                mtl1.set("二娃_")
                Log.e("Main2Activity","$name1 mtl1设置值后：mtl1.get()=${mtl1.get()}")

                Thread.sleep(200)

                //mtl1调用remove
                mtl1.remove()
                Log.e("Main2Activity","$name1 mtl1调用remove后：mtl1.get()=${mtl1.get()}")

                Log.e("Main2Activity","$name1 线程运行结束---------------------")
            }, "线程1")

            val thread2 = MockThread(Runnable {
                val name2 = Thread.currentThread().name

                //mtl2未设置值
                Log.e("Main2Activity","$name2 mtl2未设置值时：mtl2.get()=${mtl2.get()}")

                //mtl2设置值：true
                mtl2.set(true)
                Log.e("Main2Activity","$name2 mtl2设置值后：mtl2.get()=${mtl2.get()}")

                Log.e("Main2Activity","$name2 获取mtl1的值：mtl1.get()=${mtl1.get()}")

                Log.e("Main2Activity","$name2 线程运行结束---------------------")
            }, "线程2")

            thread1.start()
            thread2.start()
        }
    }
}
