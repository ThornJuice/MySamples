package com.ju.recyclerview.appbarlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ju.recyclerview.R
import com.ju.recyclerview.SimpleAdapter
import com.ju.recyclerview.User
import kotlinx.android.synthetic.main.activity_simple_ceiling.*

/**
 * 常用吸顶效果
 * */
class SimpleCeilingActivity : AppCompatActivity() {
    var users = ArrayList<User>()
    val adapter: SimpleAdapter by lazy {
        SimpleAdapter(this@SimpleCeilingActivity, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_ceiling)
        for (i in 1..50) {
            User().apply {
                this.name = "name$i"
                this.itemType = i % 2
                users.add(this)
            }

        }
        adapter.data = users
        val linearManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearManager
        recyclerView.adapter =adapter
    }
}