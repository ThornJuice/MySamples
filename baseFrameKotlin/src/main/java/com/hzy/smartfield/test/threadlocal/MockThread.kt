package com.hzy.smartfield.test.threadlocal

class MockThread(target: Runnable, name: String) : Thread(target, name) {
    private val threadLocals: MockThreadLocal.ThreadLocalMap? = null

}
