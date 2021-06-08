package com.ju.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var users = ArrayList<User>()
    val adapter: SimpleAdapter by lazy {
        SimpleAdapter(this@MainActivity, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        for (i in 1..100) {
            User().apply {
                this.name = "name" + i
                this.itemType = i % 2
                users.add(this)
            }

        }
        adapter.data = users
        val linearManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = linearManager
        recyclerView?.adapter =adapter
    }
}