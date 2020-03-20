package com.hzy.smartfield.test.threadlocal

class MockThread(target: Runnable, name: String) : Thread(target, name) {
    var threadLocals: MockThreadLocal.ThreadLocalMap? = null

}
