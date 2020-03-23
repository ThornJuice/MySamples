package com.hzy.smartfield.test

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val user = User()
//        user.name = "tom"
//        user.name = "jack"
        var s = "ssss"
//        println(s)
//
//        user.foo(1,2) {
//            println("hello")
//        }


        // user.method(ReentrantLock(),{ println("我是body的方法体")})
        // user.test(s,{println("test")})
//        val numbers = listOf("one", "two", "three", "four", "five", "six")
//         numbers.clear()
//        val numbers2 = arrayListOf<String>("four", "one", "two")
//        println(numbers.any())
//        println(numbers2.any())
//        println(numbers.isEmpty())
//        println(numbers2.isEmpty())
//        println(numbers.firstOrNull())
//        println(numbers2.firstOrNull())
//        println(numbers.firstOrNull {
//            it.endsWith("e")
//        })
//        println(numbers.random())
//          println(numbers.containsAll(numbers2))
//
//        println("Sorted ascending: ${numbers.sorted()}")
//        println("Sorted descending: ${numbers.sortedDescending()}")
//        println(numbers.takeWhile { !it.startsWith("f") })
        val numbers = listOf(5, 2, 10, 4)

//        val sum = numbers.fold(1) { sum, element -> sum + element }
//        println(sum)
//        val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
//        println(sumDoubled)
        println(numbers.random())
    }

}
