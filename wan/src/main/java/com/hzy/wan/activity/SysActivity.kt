package com.hzy.wan.activity

import android.os.Bundle
import androidx.annotation.Nullable
import com.google.android.material.tabs.TabLayout
import com.hzy.wan.R
import com.hzy.wan.adapter.ViewPageAdapter
import com.hzy.wan.bean.SystemBean
import com.hzy.wan.fragment.SysListFragment
import com.hzy.wan.fragment.SysListFragment2
import com.ju.baselibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sys.*

class SysActivity : BaseActivity() {
    var mList: List<SystemBean.DataBean.ChildrenBean>? = null
    var fragmentsList = ArrayList<SysListFragment2>()
    override fun getLayoutId(): Int {
        return R.layout.activity_sys
    }

    override fun initView() {
        setPageTitle(intent?.extras?.getString("title"))
    }

    override fun initData() {
        mList = intent?.extras?.getSerializable("child") as List<SystemBean.DataBean.ChildrenBean>
        mList?.run {
            setVpData(this)
        }

    }

    private fun setVpData(list: List<SystemBean.DataBean.ChildrenBean>) {
        if (list.isEmpty()) return
        val tabs = ArrayList<String>()
        for (i in list.indices) {
            tabs.add(list[i].name)
            val fragment = SysListFragment2()
            val bundle = Bundle()
            bundle.putInt("id", list[i].id)
            fragment.arguments = bundle
            fragmentsList.add(fragment)
        }
        val adapter = object : ViewPageAdapter(supportFragmentManager, fragmentsList) {
            @Nullable
            override fun getPageTitle(position: Int): CharSequence {
                return tabs[position]
            }
        }
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = tabs.size
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

}
