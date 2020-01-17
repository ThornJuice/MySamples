package com.hzy.wan

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hzy.wan.fragment.HomeFragment
import com.hzy.wan.fragment.OfficialAccountsFragment
import com.hzy.wan.fragment.ProjectsFragment
import com.hzy.wan.fragment.SystemFragment
import com.ju.baselibrary.base.BaseActivity
import com.ju.baselibrary.base.BaseApp

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var fragmentManager: FragmentManager? = null
    private var transaction: FragmentTransaction? = null
    private var homeFragment: HomeFragment? = null
    private var officialAccountsFragment: OfficialAccountsFragment? = null
    private var projectsFragment: ProjectsFragment? = null
    private var systemFragment: SystemFragment? = null


    override fun initView() {
        BaseApp.getAppContext()
        baseTitleBar.visibility = View.GONE
        fragmentManager = supportFragmentManager
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        bottomNavigationBar
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_01, "首页"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_02, "公众号"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_03, "体系"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_04, "项目"))
                .setActiveColor(R.color.theme)
                .setFirstSelectedPosition(0)
                .setBarBackgroundColor(R.color.white)
                .initialise()
        bottomNavigationBar.setTabSelectedListener(this)
        initHomeFragment()
    }


    override fun initData() {
    }

    private fun initHomeFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (homeFragment == null) {
            homeFragment = HomeFragment()
            transaction!!.add(R.id.container, homeFragment!!)
        }
        hideFragment(transaction!!)
        transaction!!.show(homeFragment!!)
        transaction!!.commit()
    }

    private fun initCustomerFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (officialAccountsFragment == null) {
            officialAccountsFragment = OfficialAccountsFragment()
            transaction!!.add(R.id.container, officialAccountsFragment!!)
        }

        hideFragment(transaction!!)
        transaction!!.show(officialAccountsFragment!!)
        transaction!!.commit()
    }

    private fun initHousingFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (projectsFragment == null) {
            projectsFragment = ProjectsFragment()
            transaction!!.add(R.id.container, projectsFragment!!)
        }

        hideFragment(transaction!!)
        transaction!!.show(projectsFragment!!)
        transaction!!.commit()
    }

    private fun initMineFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (systemFragment == null) {
            systemFragment = SystemFragment()
            transaction!!.add(R.id.container, systemFragment!!)
        }

        hideFragment(transaction!!)
        transaction!!.show(systemFragment!!)
        transaction!!.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment!!)
        }
        if (officialAccountsFragment != null) {
            transaction.hide(officialAccountsFragment!!)
        }
        if (projectsFragment != null) {
            transaction.hide(projectsFragment!!)
        }
        if (systemFragment != null) {
            transaction.hide(systemFragment!!)
        }
    }

    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }


    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> initHomeFragment()
            1 -> initCustomerFragment()
            2 -> initMineFragment()
            3 -> initHousingFragment()
            else -> {

            }
        }
    }
}
